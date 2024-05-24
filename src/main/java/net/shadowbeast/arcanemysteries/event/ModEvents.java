package net.shadowbeast.arcanemysteries.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.config.Config;
import net.shadowbeast.arcanemysteries.enchantments.ModEnchantments;
import net.shadowbeast.arcanemysteries.items.ModItems;
import net.shadowbeast.arcanemysteries.items.custom.HammerItem;
import net.shadowbeast.arcanemysteries.levitation_staff.PlayerLevitationTag;
import net.shadowbeast.arcanemysteries.levitation_staff.PlayerLevitationTagProvider;
import net.shadowbeast.arcanemysteries.networking.ModMessages;
import net.shadowbeast.arcanemysteries.networking.packet.LevitationDataSyncS2CPacket;
import net.shadowbeast.arcanemysteries.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ArcaneMysteries.MOD_ID)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void milkCow(@NotNull PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof LivingEntity targetEntity) {
            if (targetEntity instanceof Cow) {
                ItemStack interactionStack = event.getItemStack();
                Player player = event.getEntity();
                if (interactionStack.is(Items.GLASS_BOTTLE)) {
                    player.playSound(ModSounds.MILKING_SOUND_BOTTLE.get(), SoundSource.BLOCKS.ordinal(), 1);
                    ItemStack milkBottleStack = ItemUtils.createFilledResult(interactionStack, player, ModItems.MILK_BOTTLE.get().getDefaultInstance());
                    player.setItemInHand(event.getHand(), milkBottleStack);
                }
            }
        }
    }
    @SubscribeEvent
    public static void bakedPotatoesDamageEntity(TickEvent.PlayerTickEvent event) {
        if (Config.bakedPotatoesDoDamage) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();

            if (mainHandItem.getItem().equals(Items.BAKED_POTATO)) {
                player.hurt(player.damageSources().onFire(), 0.5F);
            }
        }
    }
    @SubscribeEvent
    public static void stackedBakedPotatoesDamageEntity(TickEvent.PlayerTickEvent event) {
        if (Config.bakedPotatoesDoDamage) {
            Player player = event.player;
            ItemStack mainHandItem = player.getMainHandItem();

            if (mainHandItem.getItem().equals(ModItems.STACKED_BAKED_POTATO.get())) {
                player.hurt(player.damageSources().onFire(), 0.7F);
            }
        }
    }
    @SubscribeEvent
    public static void onHammerUsage(@NotNull BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initalBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initalBlockPos)) {
                return;
            }
            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initalBlockPos, serverPlayer)) {
                if(pos == initalBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                // Have to add them to a Set otherwise, the same code right here will get called for each block!
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    @SubscribeEvent
    public static void addVillagerTrade(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.ICE_ASPECT.get(), 1));
        if(event.getType() == VillagerProfession.LIBRARIAN) {
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 30),
                    enchantedBook,
                    2, 8, 0.02f
            ));
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(ArcaneMysteries.MOD_ID, "properties"), new PlayerLevitationTagProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerLevitationTag.class);
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).ifPresent(levitationTag -> {
                    ModMessages.sendToPlayer(new LevitationDataSyncS2CPacket(levitationTag.isLevitationTagged()), player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onLandWithLevitationTag(LivingFallEvent event) {
        if(!event.getEntity().level().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).ifPresent(levitationTag -> {
                    levitationTag.setLevitationTagged(false);
                    ModMessages.sendToPlayer(new LevitationDataSyncS2CPacket(levitationTag.isLevitationTagged()), player);
                });
            }
        }
    }
}

//Toolsmith Trades
//            trades.get(3)
//                    .add((pEntity, pSource) -> new MerchantOffer(
//                            new ItemStack(Items.EMERALD, 27),
//                            new ItemStack(ModItems.ENDERIUM_PICKAXE.get(), 1),
//                            2, 17, 0.2f));
//            trades.get(5)
//                    .add((pEntity, pSource) -> new MerchantOffer(
//                            new ItemStack(Items.EMERALD, 48),
//                            new ItemStack(ModItems.ENDERIUM_SWORD.get(), 1),
//                            10, 30, 0.33f
//                    ));
package net.shadowbeast.arcanemysteries.registries;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.enchant.EnchantmentsRegistry;
import net.shadowbeast.arcanemysteries.items.tools.ItemHammer;
import net.shadowbeast.arcanemysteries.networking.MessagesMod;
import net.shadowbeast.arcanemysteries.networking.packet.LevitationDataSyncS2CPacket;
import net.shadowbeast.arcanemysteries.util.levitation_staff.PlayerLevitationTag;
import net.shadowbeast.arcanemysteries.util.levitation_staff.PlayerLevitationTagProvider;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ArcaneMysteries.MOD_ID)
public class EventRegistry {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void onHammerUsage(@NotNull BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof ItemHammer hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initalBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initalBlockPos)) {
                return;
            }
            for (BlockPos pos : ItemHammer.getBlocksToBeDestroyed(1, initalBlockPos, serverPlayer)) {
                if(pos == initalBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos); // Have to add them to a Set otherwise, the same code right here will get called for each block!
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    @SubscribeEvent
    public static void milkCow(@NotNull PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof LivingEntity targetEntity) {
            if (targetEntity instanceof Cow) {
                ItemStack interactionStack = event.getItemStack();
                Player player = event.getEntity();
                if (interactionStack.is(Items.GLASS_BOTTLE)) {
                    player.playSound(SoundRegistry.MILKING_SOUND_BOTTLE.get(), SoundSource.BLOCKS.ordinal(), 1);
                    ItemStack milkBottleStack = ItemUtils.createFilledResult(interactionStack, player, ItemRegistry.MILK_BOTTLE.get().getDefaultInstance());
                    player.setItemInHand(event.getHand(), milkBottleStack);
                }
            }
        }
    }
    @SubscribeEvent
    public static void addVillagerTrade(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ItemStack iceAspect = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EnchantmentsRegistry.ICE_ASPECT.get(), 1));
        ItemStack treeCapitator = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EnchantmentsRegistry.CHOPPER.get(), 1));
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                    iceAspect,
                    2, 8, 0.02f
            ));
            if (event.getType() == VillagerProfession.LIBRARIAN) {
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                        treeCapitator,
                        2, 8, 0.02f
                ));
            }
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
                event.getOriginal().getCapability(PlayerLevitationTagProvider.PLAYER_THIRST).ifPresent(newStore -> newStore.copyFrom(oldStore));
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
                    MessagesMod.sendToPlayer(new LevitationDataSyncS2CPacket(levitationTag.isLevitationTagged()), player);
                });
            }
        }
    }
    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        Player player = event.getEntity();
        if (hasMagnetismEnchant(player)) {
            double distance = player.distanceTo(event.getItem());
            if (distance <= 7) {
                event.setCanceled(true);
                event.getItem().kill();
                player.getInventory().add(event.getItem().getItem());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (hasMagnetismEnchant(player)) {
            double range = 7.0D;
            Vec3 playerPos = player.position();
            List<ItemEntity> itemsInRange = player.level().getEntitiesOfClass(ItemEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (ItemEntity item : itemsInRange) {
                Vec3 direction = playerPos.subtract(item.position()).normalize().scale(0.1); // scale the direction to control speed
                item.setDeltaMovement(item.getDeltaMovement().add(direction));
            }
        }
    }

    private static boolean hasMagnetismEnchant(Player player) {
        for (ItemStack item : player.getInventory().armor) {
            if (EnchantmentHelper.getEnchantments(item).containsKey(EnchantmentsRegistry.MAGNETISM.get())) {
                return true;
            }
        }
        return false;
    }
}

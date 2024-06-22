package net.shadowbeast.arcanemysteries.registries;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.enchant.EnchantmentsRegistry;
import net.shadowbeast.arcanemysteries.items.tools.ItemHammer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ParametersAreNonnullByDefault
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
        ItemStack chopperBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EnchantmentsRegistry.CHOPPER.get(), 1));
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                    iceAspect,
                    2, 8, 0.02f
            ));
            if (event.getType() == VillagerProfession.LIBRARIAN) {
                trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, pRandom.nextInt(12) + 36),
                        chopperBook,
                        2, 8, 0.02f
                ));
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        Player player = event.getEntity();
        int level = getMagnetismLevel(player);
        if (level > 0) {
            double distance = player.distanceTo(event.getItem());
            if (distance <= 3.0D * level) {
                event.setCanceled(true);
                event.getItem().kill();
                player.getInventory().add(event.getItem().getItem());
            }
        }
    }
    /**
     * It checks the level of the Magnetism enchantment on the players boots. If the level is greater than 0, it calculates the range from the enchantment level, then looks for any item entities in the area. If there is a item in range it calculates a direction vector pointing from the item to the player, normalizes and scales this vector, and adds it to the item's current movement vector, fancy smancy code talk, but it basically sends it to the player
     * @apiNote This method is triggered every tick
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        int level = getMagnetismLevel(player);
        if (level > 0) {
            double range = 3.0D * level;
            Vec3 playerPos = player.position();
            List<ItemEntity> itemsInRange = player.level().getEntitiesOfClass(ItemEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (ItemEntity item : itemsInRange) {
                Vec3 direction = playerPos.subtract(item.position()).normalize().scale(0.1);
                item.setDeltaMovement(item.getDeltaMovement().add(direction));
            }
        }
    }
    /**
     * This method checks the level of the Magnetism enchantment on the players boots
     * @return The level of the Magnetism enchantment if they have it, otherwise returns null/0
     */
    @SuppressWarnings("depreciation")
    private static int getMagnetismLevel(Player player) {
        for (ItemStack item : player.getInventory().armor) {
            if (EnchantmentHelper.getEnchantments(item).containsKey(EnchantmentsRegistry.MAGNETISM.get())) {
                return EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsRegistry.MAGNETISM.get(), item);
            }
        }
        return 0;
    }
}

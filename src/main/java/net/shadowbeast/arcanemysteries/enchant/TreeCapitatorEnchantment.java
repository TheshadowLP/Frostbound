package net.shadowbeast.arcanemysteries.enchant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import net.minecraft.world.level.Level;


public class TreeCapitatorEnchantment extends Enchantment {
    protected TreeCapitatorEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 10 + 20 * (pEnchantmentLevel - 1);
    }
    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return super.getMinCost(pEnchantmentLevel) + 50;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!event.getState().is(BlockTags.LOGS)) {
            return;
        }

        if (EnchantmentHelper.getItemEnchantmentLevel(this, event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND)) > 0) {
            breakAllConnectedLogs((Level) event.getLevel(), event.getPos());
        }
    }

    private void breakAllConnectedLogs(Level world, BlockPos pos) {
        Set<BlockPos> visited = new HashSet<>();
        Stack<BlockPos> stack = new Stack<>();
        stack.push(pos);

        while (!stack.isEmpty()) {
            BlockPos currentPos = stack.pop();
            visited.add(currentPos);

            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = currentPos.relative(direction);

                if (!visited.contains(neighborPos) && world.getBlockState(neighborPos).is(BlockTags.LOGS)) {
                    world.destroyBlock(neighborPos, true);
                    stack.push(neighborPos);
                }
            }
        }
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof AxeItem;
    }

}


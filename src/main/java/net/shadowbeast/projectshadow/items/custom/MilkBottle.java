package net.shadowbeast.projectshadow.items.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.shadowbeast.projectshadow.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

public class MilkBottle extends Item {
    public MilkBottle(Properties pProperties) {
        super(pProperties);
    }
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (pEntityLiving instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) pEntityLiving, pStack);
            ((ServerPlayer) pEntityLiving).awardStat(Stats.ITEM_USED.get(this));
        }
        if (!pLevel.isClientSide) {
            pEntityLiving.removeAllEffects();
        }
        if (pStack.isEmpty()) {
            pStack.shrink(1);
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (pEntityLiving instanceof Player && !((Player) pEntityLiving).getAbilities().instabuild) {
                pStack.shrink(1);
                ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                if (!((Player) pEntityLiving).getInventory().add(itemStack)) {
                    ((Player) pEntityLiving).drop(itemStack, false);
                }
            }
            return pStack;
        }
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.DRINK;
    }
    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 28;
    }
    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
    @Override
    public @NotNull SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
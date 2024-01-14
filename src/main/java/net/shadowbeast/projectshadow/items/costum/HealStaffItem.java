package net.shadowbeast.projectshadow.items.costum;


import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HealStaffItem extends Item {
    public HealStaffItem(Properties pProperties) {
        super(pProperties);
    }
    private int timer;
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {

        if (this.timer <= 0) //check if timer is less or equal than 0
        {
            if (!pLevel.isClientSide()) //check if the item is used on the server and not the client
            {
                timer = 1200; //The time you want for the timer in ticks
                pPlayer.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 20, false, false)); //Apply the effect to the player

                pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer,
                        player1 -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
            }

            pPlayer.playSound(SoundEvents.ALLAY_THROW, 1f, 1f);
            pPlayer.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1f, 1f);

        }
        else //if timer is more than 0
        {
            pPlayer.playSound(SoundEvents.CHEST_LOCKED, 0.1f, 2f);
        }
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public void inventoryTick(@NotNull ItemStack pStack, Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {

        if (!pLevel.isClientSide())
        {
            if(timer >= 0){
                timer--;
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.projectshadow.heal_staff.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.projectshadow.heal_staff"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}




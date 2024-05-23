package net.shadowbeast.projectshadow.items.custom;


import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.shadowbeast.projectshadow.levitation_staff.PlayerLevitationTagProvider;
import net.shadowbeast.projectshadow.networking.ModMessages;
import net.shadowbeast.projectshadow.networking.packet.AddLevitationTagC2SPacket;
import net.shadowbeast.projectshadow.particle.ModParticles;
import net.shadowbeast.projectshadow.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LevitationStaffItem extends Item {
    public LevitationStaffItem(Properties pProperties) {
        super(pProperties);
    }
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {

        if (pPlayer instanceof Player) {
            if (!pPlayer.getAbilities().instabuild){
                pPlayer.getCooldowns().addCooldown(this, 0);}
            if(pLevel.isClientSide()){
                for (int i = 5; i < 20; i++) {
                    double x = pPlayer.getX() + (pLevel.random.nextDouble() - 0.5) * 2;
                    double y = pPlayer.getY() + pLevel.random.nextDouble() * 2;
                    double z = pPlayer.getZ() + (pLevel.random.nextDouble() - 0.5) * 2;
                    pLevel.addParticle(ModParticles.FEATHER_PARTICLES.get(), x, y, z, 0, 0, 0);
                }
            }

            if (!pLevel.isClientSide())
            {
                List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, pPlayer.getBoundingBox().inflate(1));
                for (LivingEntity livingEntity : entities) {
                    float max_health = livingEntity.getMaxHealth();
                    if (max_health < 30F) {
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, livingEntity.isShiftKeyDown() ? 8 : 15, 20, false, false));
                    }
                }
                ModMessages.sendToServer(new AddLevitationTagC2SPacket());
                pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer,
                        player1 -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
            }
            pPlayer.playSound(ModSounds.LEVITATION_STAFF.get(), 1f, 1f);
        }
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {return false;}
    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {return false;}
    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pStack, @NotNull ItemStack pRepairCandidate) {return false;}
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.projectshadow.levitation_staff.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.projectshadow.shift_for_info"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}




package net.shadowbeast.arcanemysteries.items.staffs;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemHealStaff extends ItemStaff {

    public ItemHealStaff() {
        super(10, "healing", Rarity.create("red", ChatFormatting.RED));
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {

        if (!pPlayer.getAbilities().instabuild)
            pPlayer.getCooldowns().addCooldown(this, 900);

        if (!pLevel.isClientSide()) {
            int heartsToFill = (int) (pPlayer.getMaxHealth() - pPlayer.getHealth());
            for (int i = 0; i < heartsToFill + pPlayer.getRandom().nextIntBetweenInclusive(2, 8); i++) {
                double d0 = pPlayer.getRandom().nextGaussian() * 0.02D;
                double d1 = pPlayer.getRandom().nextGaussian() * 0.02D;
                double d2 = pPlayer.getRandom().nextGaussian() * 0.02D;
                ((ServerLevel) pLevel).sendParticles(ParticleTypes.HEART, pPlayer.getRandomX(1.0D), pPlayer.getRandomY() + 0.5D, pPlayer.getRandomZ(1.0D), 1, d0, d1, d2, 0);
            }
            pPlayer.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 20, false, false));

            pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer,
                    player1 -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
        }
        pLevel.playSound(null, pPlayer.blockPosition(), SoundEvents.ALLAY_THROW, SoundSource.PLAYERS, 1f, 1f);
        pLevel.playSound(null, pPlayer.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1f, 1f);

        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
}

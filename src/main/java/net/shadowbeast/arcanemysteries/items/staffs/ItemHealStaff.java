package net.shadowbeast.arcanemysteries.items.staffs;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
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
        super(10);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {

        if (pPlayer instanceof Player) {
            if (!pPlayer.getAbilities().instabuild){
                pPlayer.getCooldowns().addCooldown(this, 900);}

            if (!pLevel.isClientSide())
            {
                pPlayer.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 20, false, false));

                pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer,
                        player1 -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
            }
            for(int i = 0; i < pPlayer.getRandom().nextIntBetweenInclusive(5, 10); i++) {
                double d0 = pPlayer.getRandom().nextGaussian() * 0.02D;
                double d1 = pPlayer.getRandom().nextGaussian() * 0.02D;
                double d2 = pPlayer.getRandom().nextGaussian() * 0.02D;
                pLevel.addParticle(ParticleTypes.HEART, pPlayer.getRandomX(1.0D), pPlayer.getRandomY() + 0.5D, pPlayer.getRandomZ(1.0D), d0, d1, d2);
            }
            pPlayer.playSound(SoundEvents.ALLAY_THROW, 1f, 1f);
            pPlayer.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1f, 1f);
        }
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    @Override
    String tooltip() {
        return "healing";
    }

    @Override
    public ChatFormatting color() {
        return ChatFormatting.LIGHT_PURPLE;
    }
}

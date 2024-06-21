package net.shadowbeast.arcanemysteries.items.staffs;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.interfaces.util.IFallDamageCancelable;
import net.shadowbeast.arcanemysteries.registries.ParticleRegistry;
import net.shadowbeast.arcanemysteries.registries.SoundRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemLevitationStaff extends ItemStaff {

    public ItemLevitationStaff() {
        super(18, "levitation", Rarity.UNCOMMON);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {

        if (!pPlayer.getAbilities().instabuild)
            pPlayer.getCooldowns().addCooldown(this, 300);

        if (!pLevel.isClientSide()) {
            for (int i = 5; i < 20; i++) {
                double d0 = pPlayer.getX() + (pLevel.random.nextDouble() - 0.5) * 2;
                double d1 = pPlayer.getY() + pLevel.random.nextDouble() * 2;
                double d2 = pPlayer.getZ() + (pLevel.random.nextDouble() - 0.5) * 2;
                ((ServerLevel) pLevel).sendParticles(ParticleRegistry.FEATHER_PARTICLES.get(), d0, d1, d2, 1, 0, 0, 0, 0);
            }
            List<LivingEntity> entities = pLevel.getEntitiesOfClass(LivingEntity.class, pPlayer.getBoundingBox().inflate(1));
            for (LivingEntity livingEntity : entities) {
                float max_health = livingEntity.getMaxHealth();
                if (max_health < 30F) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, livingEntity.isShiftKeyDown() ? 8 : 15, 20, false, false));
                }
            }
            pPlayer.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 20, false, false));

            pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer,
                    player1 -> pPlayer.broadcastBreakEvent(pPlayer.getUsedItemHand()));
        }
        ((IFallDamageCancelable) pPlayer).setArcaneMysteries$cancelFallDamage(true);
        pLevel.playSound(null, pPlayer.blockPosition(), SoundRegistry.LEVITATION_STAFF.get(), SoundSource.PLAYERS, 1f, 1f);
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
}

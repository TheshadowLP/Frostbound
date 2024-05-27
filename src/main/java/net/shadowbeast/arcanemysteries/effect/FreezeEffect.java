package net.shadowbeast.arcanemysteries.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.arcanemysteries.registries.EffectsRegistry;

import java.util.Objects;

public class FreezeEffect extends MobEffect {
    public FreezeEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            pLivingEntity.setTicksFrozen(pLivingEntity.getTicksRequiredToFreeze() + pLivingEntity.getEffect(EffectsRegistry.FREEZE.get()).getDuration());
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}


package net.shadowbeast.projectshadow.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class MuddiedEyesEffect extends MobEffect {
    public MuddiedEyesEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            // pAmbient does something, don't know what
            // Also, leave pVisible to "false" so it does not show particles
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, pAmplifier, false, false));


        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {return true;}
}

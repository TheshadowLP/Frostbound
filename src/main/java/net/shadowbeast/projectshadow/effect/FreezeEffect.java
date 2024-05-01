package net.shadowbeast.projectshadow.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.PowderSnowBlock;

public class FreezeEffect extends MobEffect {
    public FreezeEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            // pAmbient does something, don't know what //TODO we need to add small instant damage like you are freeze burn(three hearts per hit and movement slowdown for 10 second for freeze I and for II 20 seconds, and also adding the snow powder freeze overlay
            // Also, leave pVisible to "false" so it does not show particles
            //pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HARM, 1, pAmplifier, false, false));
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {return true;}
}


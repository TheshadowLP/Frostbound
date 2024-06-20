package net.shadowbeast.arcanemysteries.effect;

import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.interfaces.util.IRealisticEntity;
import net.shadowbeast.arcanemysteries.registries.EffectsRegistry;

public class MobEffectS extends MobEffect {

    public MobEffectS(MobEffectCategory effectType, int liquidColorIn) {
        super(effectType, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn instanceof IRealisticEntity) {
            IRealisticEntity realisticEntity = (IRealisticEntity)entityLivingBaseIn;
            if (this == EffectsRegistry.DEPRECIATED_HYPOTHERMIA.get() && entityLivingBaseIn instanceof Player) {
                boolean flag = !entityLivingBaseIn.level().getDifficulty().equals(Difficulty.HARD) && entityLivingBaseIn.getHealth() > 1.0F;
                if (entityLivingBaseIn.level().getDifficulty().equals(Difficulty.HARD)) flag = true;
                if ((!((Player)entityLivingBaseIn).isSleeping()) && flag) {
                    entityLivingBaseIn.hurt(ADamageSources.source(entityLivingBaseIn.level().registryAccess(), ADamageTypes.HYPOTHERMIA), 0.4F);
                }
            } else if (this == EffectsRegistry.DEPRECIATED_HYPERTHERMIA.get()&& entityLivingBaseIn instanceof Player) {
                boolean flag = !entityLivingBaseIn.level().getDifficulty().equals(Difficulty.HARD) && entityLivingBaseIn.getHealth() > 1.0F;
                if (entityLivingBaseIn.level().getDifficulty().equals(Difficulty.HARD)) flag = true;
                if ((!((Player)entityLivingBaseIn).isSleeping()) && flag) {
                    entityLivingBaseIn.hurt(ADamageSources.source(entityLivingBaseIn.level().registryAccess(), ADamageTypes.HYPERTHERMIA), 0.4F);
                }
            }
        }
        super.applyEffectTick(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        if (this == EffectsRegistry.DEPRECIATED_HYPOTHERMIA.get()) {
            int k = 40 >> amplifier;
            if (k > 0) {
                return duration % k == 0;
            } else {
                return true;
            }
        } else if (this == EffectsRegistry.DEPRECIATED_HYPERTHERMIA.get()) {
            int k = 40 >> amplifier;
            if (k > 0) {
                return duration % k == 0;
            } else {
                return true;
            }
        } else if (this == EffectsRegistry.CHILLED.get()) {
            int k = 60 >> amplifier;
            if (k > 0) {
                return duration % k == 0;
            } else {
                return true;
            }
        } else if (this == EffectsRegistry.HEATED.get()) {
            int k = 60 >> amplifier;
            if (k > 0) {
                return duration % k == 0;
            } else {
                return true;
            }
        } 
         else {
            return this == EffectsRegistry.CHILLED.get();
        }
    }
}

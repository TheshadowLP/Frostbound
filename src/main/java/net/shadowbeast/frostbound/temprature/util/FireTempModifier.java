package net.shadowbeast.frostbound.temprature.util;

import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.frostbound.api.temperature.TempModifier;
import net.shadowbeast.frostbound.temprature.Temperature;

import java.util.function.Function;

public class FireTempModifier extends TempModifier
{
    @Override
    protected Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait)
    {
        return temp -> entity.isOnFire() ? temp + 10 : temp;
    }
}
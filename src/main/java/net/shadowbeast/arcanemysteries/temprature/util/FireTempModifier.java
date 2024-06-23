package net.shadowbeast.arcanemysteries.temprature.util;

import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.arcanemysteries.api.temperature.TempModifier;
import net.shadowbeast.arcanemysteries.temprature.Temperature;

import java.util.function.Function;

public class FireTempModifier extends TempModifier
{
    @Override
    protected Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait)
    {
        return temp -> entity.isOnFire() ? temp + 10 : temp;
    }
}
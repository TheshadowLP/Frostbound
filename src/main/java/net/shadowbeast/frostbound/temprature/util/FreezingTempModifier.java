package net.shadowbeast.frostbound.temprature.util;

import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.frostbound.api.temperature.TempModifier;
import net.shadowbeast.frostbound.temprature.Temperature;

import java.util.function.Function;

public class FreezingTempModifier extends TempModifier
{
    public FreezingTempModifier(double chill)
    {
        this.getNBT().putDouble("chill", chill);
    }

    public FreezingTempModifier()
    {
        this(0);
    }

    @Override
    public Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait)
    {
        return temp -> temp - this.getNBT().getDouble("chill");
    }
}
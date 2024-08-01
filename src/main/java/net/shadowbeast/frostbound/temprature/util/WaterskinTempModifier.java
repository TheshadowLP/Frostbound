package net.shadowbeast.frostbound.temprature.util;

import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.frostbound.api.temperature.TempModifier;
import net.shadowbeast.frostbound.temprature.Temperature;

import java.util.function.Function;

public class WaterskinTempModifier extends TempModifier
{
    public WaterskinTempModifier()
    {   this(0.0);
    }

    public WaterskinTempModifier(double temp)
    {   this.getNBT().putDouble("Temperature", temp);
    }

    @Override
    public Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait)
    {   return temp -> temp + this.getNBT().getDouble("Temperature");
    }
}
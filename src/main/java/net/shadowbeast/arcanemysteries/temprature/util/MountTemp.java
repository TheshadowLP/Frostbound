package net.shadowbeast.arcanemysteries.temprature.util;

import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.arcanemysteries.api.temperature.TempModifier;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.util.MathHelper;

import java.util.function.Function;

public class MountTemp extends TempModifier
{
    public MountTemp()
    {   this(0, 0);
    }

    public MountTemp(double coldInsul, double heatInsul)
    {   this.getNBT().putDouble("ColdInsulation", coldInsul);
        this.getNBT().putDouble("HeatInsulation", heatInsul);
    }

    @Override
    public Function<Double, Double> calculate(LivingEntity entity, Temperature.Trait trait)
    {
        return temp ->
        {   String toChange = temp > 0 ? "HeatInsulation" : "ColdInsulation";
            return MathHelper.blend(temp, 0, this.getNBT().getDouble(toChange), 0, 1);
        };
    }
}

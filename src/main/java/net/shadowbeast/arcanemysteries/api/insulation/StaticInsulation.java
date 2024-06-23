package net.shadowbeast.arcanemysteries.api.insulation;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.shadowbeast.arcanemysteries.util.MathHelper;

import java.util.ArrayList;
import java.util.List;
public class StaticInsulation extends Insulation
{
    public static final Codec<StaticInsulation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("cold").forGetter(StaticInsulation::getCold),
            Codec.DOUBLE.fieldOf("heat").forGetter(StaticInsulation::getHeat)
    ).apply(instance, StaticInsulation::new));

    private final double cold;
    private final double heat;

    public StaticInsulation(double cold, double heat)
    {   this.cold = cold;
        this.heat = heat;
    }

    public StaticInsulation(Pair<? extends Number, ? extends Number> pair)
    {   this(pair.getFirst().doubleValue(), pair.getSecond().doubleValue());
    }

    public double getCold()
    {   return cold;
    }

    public double getHeat()
    {   return heat;
    }

    @Override
    public boolean isEmpty()
    {   return cold == 0 && heat == 0;
    }

    @Override
    public List<Insulation> split()
    {
        List<Insulation> insulation = new ArrayList<>();
        double cold = this.getCold();
        double heat = this.getHeat();
        double neutral = cold > 0 == heat > 0 ? MathHelper.minAbs(cold, heat) : 0;
        cold -= neutral;
        heat -= neutral;

        // Cold insulation
        for (int i = 0; i < MathHelper.ceil(Math.abs(cold)) / 2; i++)
        {   double coldInsul = MathHelper.minAbs(MathHelper.shrink(cold, i * 2), 2);
            insulation.add(new StaticInsulation(coldInsul, 0d));
        }

        // Neutral insulation
        for (int i = 0; i < MathHelper.ceil(Math.abs(neutral)); i++)
        {   double neutralInsul = MathHelper.minAbs(MathHelper.shrink(neutral, i), 1);
            insulation.add(new StaticInsulation(neutralInsul, neutralInsul));
        }

        // Heat insulation
        for (int i = 0; i < MathHelper.ceil(Math.abs(heat)) / 2; i++)
        {   double heatInsul = MathHelper.minAbs(MathHelper.shrink(heat, i * 2), 2);
            insulation.add(new StaticInsulation(0d, heatInsul));
        }
        return insulation;
    }

    @Override
    public String toString()
    {   return "Insulation{" + "cold=" + cold + ", heat=" + heat + '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        return obj instanceof StaticInsulation insul
                && cold == insul.cold
                && heat == insul.heat;
    }

    @Override
    public CompoundTag serialize()
    {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("cold", cold);
        tag.putDouble("heat", heat);
        return tag;
    }

    public static StaticInsulation deserialize(CompoundTag tag)
    {   return new StaticInsulation(tag.getDouble("cold"), tag.getDouble("heat"));
    }
}
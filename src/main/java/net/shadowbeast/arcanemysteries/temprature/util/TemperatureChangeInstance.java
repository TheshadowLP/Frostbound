package net.shadowbeast.arcanemysteries.temprature.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public abstract class TemperatureChangeInstance
{
    public float temperature;

    public TemperatureChangeInstance(float temp)
    {
        this.temperature = temp;
    }

    public abstract boolean changeTemperature(Player player);

    public abstract CompoundTag serialize();

    public float getTemperature(){
        return temperature;
    }

    @Nullable
    public Component getAdditionalInfo()
    {
        return null;
    }

}

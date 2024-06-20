package net.shadowbeast.arcanemysteries.temprature.condition;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureChangeInstance;


public abstract class TemperatureChangeCondition<T extends TemperatureChangeInstance> {

    public TemperatureChangeCondition(){}

    public abstract T createCondition(JsonObject obj);
    public abstract T createCondition(CompoundTag tag);
}

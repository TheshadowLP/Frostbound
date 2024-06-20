package net.shadowbeast.arcanemysteries.temprature.condition;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureChangeInstance;


public class DefaultCondition extends TemperatureChangeCondition<DefaultCondition.Instance>
{
    public DefaultCondition()
    {
        super();
    }


    @Override
    public Instance createCondition(JsonObject obj)
    {
        float temperatureIn = 0;
        if(obj.has("temperature") && obj.get("temperature").isJsonPrimitive()){
            temperatureIn = obj.get("temperature").getAsFloat();
        }
        return new Instance(temperatureIn);
    }

    @Override
    public Instance createCondition(CompoundTag tag)
    {
        float temperatureIn = tag.getFloat("temperature");
        return new Instance(temperatureIn);
    }

    static class Instance extends TemperatureChangeInstance
    {

        public Instance(float temp) {
            super(temp);
        }

        @Override
        public boolean changeTemperature(Player player) {
            return true;
        }

        @Override
        public CompoundTag serialize() {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("temperature",this.getTemperature());
            return tag;
        }

    }
}

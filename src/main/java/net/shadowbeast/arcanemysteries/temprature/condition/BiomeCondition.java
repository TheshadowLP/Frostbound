package net.shadowbeast.arcanemysteries.temprature.condition;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureChangeInstance;
import net.shadowbeast.arcanemysteries.util.RegistryHelper;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class BiomeCondition extends TemperatureChangeCondition<BiomeCondition.Instance>
{
    public BiomeCondition()
    {
        super();
    }


    @Override
    public Instance createCondition(JsonObject obj)
    {
        float temperatureIn = 0;
        String biome = "";
        if(obj.has("temperature") && obj.get("temperature").isJsonPrimitive()){
            temperatureIn = obj.get("temperature").getAsFloat();
        }
        if(obj.has("biome") && obj.get("biome").isJsonPrimitive()){
            biome = obj.get("biome").getAsString();
        }
        return new Instance(temperatureIn,new ResourceLocation(biome));
    }

    @Override
    public Instance createCondition(CompoundTag tag)
    {
        float temp = tag.getFloat("temperature");
        String biome = tag.getString("biome");
        return new Instance(temp, new ResourceLocation(biome));
    }

    static class Instance extends TemperatureChangeInstance
    {
        private ResourceLocation biome;

        public Instance(float temperatureIn, ResourceLocation biomeIn) {
            super(temperatureIn);
            this.biome = biomeIn;
        }

        @Override
        public boolean changeTemperature(Player player)
        {
            if(player.level().getBiome(player.blockPosition()).unwrapKey().isPresent()){
                return RegistryHelper.matchesRegistryKey(this.biome,player.level().getBiome(player.blockPosition()).unwrapKey().get());
            }
            else return false;
        }

        @Override
        public CompoundTag serialize() {
            CompoundTag nbt = new CompoundTag();
            nbt.putFloat("temperature", this.getTemperature());
            nbt.putString("biome", this.biome.toString());
            return nbt;
        }

        @Override
        public @Nullable Component getAdditionalInfo() {
            return super.getAdditionalInfo();
        }
    }
}

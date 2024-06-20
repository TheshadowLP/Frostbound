package net.shadowbeast.arcanemysteries.temprature.condition;


import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureChangeInstance;
import net.shadowbeast.arcanemysteries.util.RegistryHelper;

public class BiomeNotCondition extends TemperatureChangeCondition<BiomeNotCondition.Instance>{

    public BiomeNotCondition(){
        super();
    }

    @Override
    public Instance createCondition(JsonObject object) {
        float temperatureIn = 0;
        String biomeIn = "";
        if(object.has("temperature") && object.get("temperature").isJsonPrimitive()) {
            temperatureIn = object.get("temperature").getAsFloat();
        }
        if(object.has("biome") && object.get("biome").isJsonPrimitive()) {
            biomeIn = object.get("biome").getAsString();
        }
        return new Instance(temperatureIn, new ResourceLocation(biomeIn));
    }

    @Override
    public Instance createCondition(CompoundTag nbt) {
        float temperatureIn = nbt.getFloat("temperature");
        String biomeIn = nbt.getString("biome");
        return new Instance(temperatureIn, new ResourceLocation(biomeIn));
    }


    static class Instance extends TemperatureChangeInstance{
        ResourceLocation biome;
        public Instance(float temp, ResourceLocation biome) {
            super(temp);
            this.biome = biome;
        }

        @Override
        public boolean changeTemperature(Player player) {
            if (player.level().getBiome(player.blockPosition()).unwrapKey().isPresent())
                return !RegistryHelper.matchesRegistryKey(this.biome, player.level().getBiome(player.blockPosition()).unwrapKey().get());
            else return true;
        }

        @Override
        public CompoundTag serialize() {
            CompoundTag nbt = new CompoundTag();
            nbt.putFloat("temperature", this.getTemperature());
            nbt.putString("biome", this.biome.toString());
            return nbt;
        }
    }
}
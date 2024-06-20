package net.shadowbeast.arcanemysteries.util;
/* Code Provided By StreoWalker */

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class BiomeJsonHolder implements JsonHolder {
    private static final Marker BLOCK_TEMPERATURE_DATA = MarkerManager.getMarker("BLOCK_TEMPERATURE_DATA");

    private ResourceLocation biomeID;
    private final float thirst_chance;
    private final int unwell_intensity;
    private final float temperature;
    private final float wetnessModifier;
    private final float sun_intensity;
    private final Pair<Float, Float> altitude_level_modifier;

    public BiomeJsonHolder(CompoundTag nbt) {
        this.biomeID = new ResourceLocation(nbt.getString("id"));
        this.thirst_chance = nbt.getFloat("thirst_chance");
        this.unwell_intensity = nbt.getInt("unwell_intensity");
        this.temperature = nbt.getFloat("temperature");
        this.wetnessModifier = nbt.getFloat("wetnessModifier");
        this.sun_intensity = nbt.getFloat("sun_intensity");
        this.altitude_level_modifier = null;
    }

    public BiomeJsonHolder(ResourceLocation biomeID, JsonObject object) {
        String ALTITUDE_LEVEL_MODIFIER = "altitude_level_modifier";

        float temperatureIn = 0;
        float sunIntensitIn = 5;
        Pair<Float, Float> altitude_level_modifierIn = Pair.of(1.0f, 1.0f);

        this.biomeID = biomeID;
        this.wetnessModifier = this.workOnFloatIfAvailable("wetness_modifier", object, 1f);
        this.thirst_chance = this.workOnFloatIfAvailable("thirst_chance", object, -1f);
        this.unwell_intensity = this.workOnIntIfAvailable("unwell_intensity", object, 3);

        if(object.entrySet().size() != 0) {
            try {
                stopWorking();
                if(this.hasMemberAndIsPrimitive("temperature", object)) {
                    temperatureIn = workOnFloat("temperature", object);
                }
                if(this.hasMemberAndIsPrimitive("sun_intensity", object)) {
                    sunIntensitIn = workOnFloat("sun_intensity", object);
                }
                if (this.hasMemberAndIsObject(ALTITUDE_LEVEL_MODIFIER, object)) {
                    setWorkingOn(ALTITUDE_LEVEL_MODIFIER);
                    JsonObject sea = object.getAsJsonObject(ALTITUDE_LEVEL_MODIFIER);
                    if(sea.entrySet().size() != 0) {
                        stopWorking();
                        try {
                            if(this.hasMemberAndIsPrimitive("upper", sea)) {
                                setWorkingOn("upper");
                                altitude_level_modifierIn = Pair.of(sea.get("upper").getAsFloat(), altitude_level_modifierIn.getSecond());
                                stopWorking();
                            }
                            if (this.hasMemberAndIsPrimitive("lower", object)) {
                                setWorkingOn("lower");
                                altitude_level_modifierIn = Pair.of(altitude_level_modifierIn.getFirst(), sea.get("lower").getAsFloat());
                                stopWorking();
                            }
                        } catch (ClassCastException e) {
                        } catch (NumberFormatException e) {
                        }
                    }
                    stopWorking();
                }
            } catch (ClassCastException e) {
            } catch (NumberFormatException e) {
            }
        }

        this.temperature = temperatureIn;
        this.sun_intensity = sunIntensitIn;
        this.altitude_level_modifier = altitude_level_modifierIn;
    }

    public ResourceLocation getItemID() {
        return biomeID;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWetnessModifier() {
        return wetnessModifier;
    }

    public Pair<Float, Float> getAltitudeLevelModifier() {
        return altitude_level_modifier;
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("id", this.biomeID.toString());
        nbt.putInt("unwell_intensity", this.unwell_intensity);
        nbt.putFloat("thirst_chance", this.thirst_chance);
        nbt.putFloat("temperature", this.temperature);
        nbt.putFloat("wetnessModifier", this.wetnessModifier);
        nbt.putFloat("sun_intensity", this.sun_intensity);
        return nbt;
    }

    String wo = "NOTHING";

    @Override
    public String getworkingOn() {
        return wo;
    }

    @Override
    public void setWorkingOn(String member) {
        this.wo = member;
    }

    public float getSunIntensity() {
        return sun_intensity;
    }

    @Override
    public JsonHolder deserialize(CompoundTag input) {
        return null;
    }

    public float getThirstChance() {
        return thirst_chance;
    }

    public int getUnwellIntensity() {
        return unwell_intensity;
    }

    public float workOnFloatIfAvailable(String member, JsonObject object, float defaultValue) {
        if (object.has(member) && object.get(member).isJsonPrimitive() && object.get(member).getAsJsonPrimitive().isNumber()) {
            return object.get(member).getAsFloat();
        }
        return defaultValue;
    }

    public int workOnIntIfAvailable(String member, JsonObject object, int defaultValue) {
        if (object.has(member) && object.get(member).isJsonPrimitive() && object.get(member).getAsJsonPrimitive().isNumber()) {
            return object.get(member).getAsInt();
        }
        return defaultValue;
    }

    public boolean hasMemberAndIsPrimitive(String member, JsonObject object) {
        return object.has(member) && object.get(member).isJsonPrimitive();
    }

    public float workOnFloat(String member, JsonObject object) {
        return object.get(member).getAsFloat();
    }

    public boolean hasMemberAndIsObject(String member, JsonObject object) {
        return object.has(member) && object.get(member).isJsonObject();
    }

    public void stopWorking() {
        this.wo = "NOTHING";
    }
}

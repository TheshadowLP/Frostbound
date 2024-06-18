package net.shadowbeast.arcanemysteries.json;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.util.JsonHolder;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class EntityTemperatureJsonHolder implements JsonHolder {
    private static final Marker ENTITY_TEMPERATURE_DATA = MarkerManager.getMarker("ENTITY_TEMPERATURE_DATA");

    private ResourceLocation entityID;
    private final float temperatureModifier;
    private final float range;

    public EntityTemperatureJsonHolder(ResourceLocation blockID, JsonObject object) {
        String TEMPERATURE_MODIFIER = "temperature_modifier";
        String RANGE = "range";

        float temperatureModifierIn = 0;
        float rangeIn = 0;

        this.entityID = blockID;
        if(object.entrySet().size() != 0) {
            stopWorking();
            try {
                if(object.has(TEMPERATURE_MODIFIER) && object.get(TEMPERATURE_MODIFIER).isJsonPrimitive()) {
                    setWorkingOn(TEMPERATURE_MODIFIER);
                    temperatureModifierIn = object.get(TEMPERATURE_MODIFIER).getAsFloat();
                    stopWorking();
                }

                if(object.has(RANGE) && object.get(RANGE).isJsonPrimitive()) {
                    setWorkingOn(RANGE);
                    rangeIn = object.get(RANGE).getAsFloat();
                    stopWorking();
                }
            } catch (ClassCastException e) {
            } catch (NumberFormatException e) {
            }
        }

        if (rangeIn > 5) {
            rangeIn = 5;
        }

        if (rangeIn < 0) {
            rangeIn = 0;
        }


        this.temperatureModifier = temperatureModifierIn;
        this.range = rangeIn;

    }

    public ResourceLocation getItemID() {
        return entityID;
    }

    /**
     * @return the temperatureModifier
     */
    public float getTemperatureModifier() {
        return temperatureModifier;
    }

    /**
     * @return the range
     */
    public float getRange() {
        return range;
    }

    @Override
    public CompoundTag serialize() {
        return null;
    }

    String wo = "NOTHING";
    @Override
    public String getworkingOn() {return wo;}
    @Override
    public void setWorkingOn(String member) {this.wo = member;}

    @Override
    public JsonHolder deserialize(CompoundTag input) {
        return null;
    }
}

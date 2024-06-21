package net.shadowbeast.arcanemysteries.api;

import net.minecraftforge.eventbus.api.Event;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureModifier;

public class TemperatureModifierSetEvent extends Event {
    TemperatureModifier modifier;

    /**
     * @param modifier The modifier
     */
    public TemperatureModifierSetEvent(TemperatureModifier modifier) {
        this.modifier = modifier;
    }

    /**
     * @return modifier
     */
    public TemperatureModifier getModifier() {
        return modifier;
    }
    /**
     * @param modifier Sets the modifier
     */
    public void setModifier(TemperatureModifier modifier) {
        this.modifier = modifier;
    }
}
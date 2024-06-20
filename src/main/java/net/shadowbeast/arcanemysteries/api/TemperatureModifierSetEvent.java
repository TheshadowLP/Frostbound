package net.shadowbeast.arcanemysteries.api;

import net.minecraftforge.eventbus.api.Event;
import net.shadowbeast.arcanemysteries.temprature.util.TemperatureModifier;

public class TemperatureModifierSetEvent extends Event {
    TemperatureModifier modifier;

    public TemperatureModifierSetEvent(TemperatureModifier modifier) {
        this.modifier = modifier;
    }

    public TemperatureModifier getModifier() {
        return modifier;
    }

    public void setModifier(TemperatureModifier modifier) {
        this.modifier = modifier;
    }
}
package net.shadowbeast.frostbound.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.frostbound.config.ConfigSettings;
import net.shadowbeast.frostbound.temprature.Temperature;
import net.shadowbeast.frostbound.temprature.util.EntityTempManager;

public class ConfigPageOne extends AbstractConfigPage
{
    Screen parentScreen;

    public ConfigPageOne(Screen parentScreen)
    {
        super(parentScreen);
        if (parentScreen == null)
        {   parentScreen = Minecraft.getInstance().screen;
        }
        this.parentScreen = parentScreen;
    }

    @Override
    public Component sectionOneTitle()
    {   return Component.translatable("frostbound.config.section.temperature_details");
    }

    @Override
    public Component sectionTwoTitle()
    {   return Component.translatable("frostbound.config.section.difficulty");
    }

    @Override
    protected void init()
    {
        super.init();

        Temperature.Units[] properUnits = {ConfigSettings.CELSIUS.get() ? Temperature.Units.C : Temperature.Units.F};

        /*
         The Options
        */

        // Celsius
        this.addButton("units", Side.LEFT, () -> Component.translatable("frostbound.config.units.name").append(": ").append(ConfigSettings.CELSIUS.get()
                        ? Component.translatable("frostbound.config.celsius.name")
                        : Component.translatable("frostbound.config.fahrenheit.name")),
                button ->
                {
                    Player player = Minecraft.getInstance().player;

                    ConfigSettings.CELSIUS.set(!ConfigSettings.CELSIUS.get());
                    // Update the world temp. gauge when the button is pressed
                    if (player != null)
                        Overlays.WORLD_TEMP = Temperature.convert(EntityTempManager.getTemperatureCap(player).map(cap -> cap.getTrait(Temperature.Trait.WORLD)).orElse(0d), Temperature.Units.MC, properUnits[0], true);

                    properUnits[0] = ConfigSettings.CELSIUS.get() ? Temperature.Units.C : Temperature.Units.F;

                    // Change the max & min temps to reflect the new setting
                    ((EditBox) this.widgetBatches.get("max_temp").get(0)).setValue(String.valueOf(ConfigScreen.TWO_PLACES.format(
                            Temperature.convert(ConfigSettings.MAX_TEMP.get(), Temperature.Units.MC, properUnits[0], true))));

                    ((EditBox) this.widgetBatches.get("min_temp").get(0)).setValue(String.valueOf(ConfigScreen.TWO_PLACES.format(
                            Temperature.convert(ConfigSettings.MIN_TEMP.get(), Temperature.Units.MC, properUnits[0], true))));
                }, false, false, true, Component.translatable("frostbound.config.units.desc"));

        // Max Temperature
        this.addDecimalInput("max_temp", Side.LEFT, Component.translatable("frostbound.config.max_temperature.name"),
                value -> ConfigSettings.MAX_TEMP.set(Temperature.convert(value, properUnits[0], Temperature.Units.MC, true)),
                input -> input.setValue(String.valueOf(Temperature.convert(ConfigSettings.MAX_TEMP.get(), Temperature.Units.MC, properUnits[0], true))),
                true, false, false, Component.translatable("frostbound.config.max_temperature.desc"));

        // Min Temperature
        this.addDecimalInput("min_temp", Side.LEFT, Component.translatable("frostbound.config.min_temperature.name"),
                value -> ConfigSettings.MIN_TEMP.set(Temperature.convert(value, properUnits[0], Temperature.Units.MC, true)),
                input -> input.setValue(String.valueOf(Temperature.convert(ConfigSettings.MIN_TEMP.get(), Temperature.Units.MC, properUnits[0], true))),
                true, false, false, Component.translatable("frostbound.config.min_temperature.desc"));

        // Temp Damage
        this.addDecimalInput("temp_damage", Side.LEFT, Component.translatable("frostbound.config.temp_damage.name"),
                value -> ConfigSettings.TEMP_DAMAGE.set(value),
                input -> input.setValue(String.valueOf(ConfigSettings.TEMP_DAMAGE.get())),
                true, true, false, Component.translatable("frostbound.config.temp_damage.desc"));

        // Rate Multiplier
        this.addDecimalInput("rate", Side.LEFT, Component.translatable("frostbound.config.temperature_rate.name"),
                value -> ConfigSettings.TEMP_RATE.set(Math.max(0, value)),
                input -> input.setValue(String.valueOf(ConfigSettings.TEMP_RATE.get())),
                true, true, false, Component.translatable("frostbound.config.temperature_rate.desc"));


        this.addEmptySpace(Side.RIGHT, 1);


        // Misc. Temp Effects
        this.addButton("ice_resistance", Side.RIGHT,
                () -> Component.translatable("frostbound.config.ice_resistance.name").append(": ").append(ConfigSettings.ICE_RESISTANCE_ENABLED.get() ? ON : OFF),
                button -> ConfigSettings.ICE_RESISTANCE_ENABLED.set(!ConfigSettings.ICE_RESISTANCE_ENABLED.get()),
                true, true, false, Component.translatable("frostbound.config.ice_resistance.desc"));

        this.addButton("fire_resistance", Side.RIGHT,
                () -> Component.translatable("frostbound.config.fire_resistance.name").append(": ").append(ConfigSettings.FIRE_RESISTANCE_ENABLED.get() ? ON : OFF),
                button -> ConfigSettings.FIRE_RESISTANCE_ENABLED.set(!ConfigSettings.FIRE_RESISTANCE_ENABLED.get()),
                true, true, false, Component.translatable("frostbound.config.fire_resistance.desc"));

        this.addButton("require_thermometer", Side.RIGHT,
                () -> Component.translatable("frostbound.config.require_thermometer.name").append(": ").append(ConfigSettings.REQUIRE_THERMOMETER.get() ? ON : OFF),
                button -> ConfigSettings.REQUIRE_THERMOMETER.set(!ConfigSettings.REQUIRE_THERMOMETER.get()),
                true, true, false, Component.translatable("frostbound.config.require_thermometer.desc"));

        this.addButton("damage_scaling", Side.RIGHT,
                () -> Component.translatable("frostbound.config.damage_scaling.name").append(": ").append(ConfigSettings.DAMAGE_SCALING.get() ? ON : OFF),
                button -> ConfigSettings.DAMAGE_SCALING.set(!ConfigSettings.DAMAGE_SCALING.get()),
                true, true, false, Component.translatable("frostbound.config.damage_scaling.desc"));
    }
}
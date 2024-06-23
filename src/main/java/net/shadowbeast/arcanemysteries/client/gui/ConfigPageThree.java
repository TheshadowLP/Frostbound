package net.shadowbeast.arcanemysteries.client.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.shadowbeast.arcanemysteries.config.ConfigSettings;

import javax.annotation.Nullable;

public class ConfigPageThree extends AbstractConfigPage
{
    public ConfigPageThree(Screen parentScreen)
    {   super(parentScreen);
    }

    @Override
    public Component sectionOneTitle()
    {   return Component.translatable("arcanemysteries.config.section.other");
    }

    @Nullable
    @Override
    public Component sectionTwoTitle()
    {   return Component.translatable("arcanemysteries.config.section.difficulty");
    }

    @Override
    protected void init()
    {
        super.init();

        // Enable Grace Period
        this.addButton("grace_toggle", Side.LEFT, () -> Component.translatable("arcanemysteries.config.grace_period.name").append(": ").append(ConfigSettings.GRACE_ENABLED.get() ? ON : OFF),
                button ->
                {
                    ConfigSettings.GRACE_ENABLED.set(!ConfigSettings.GRACE_ENABLED.get());
                    button.setMessage(Component.literal(Component.translatable("arcanemysteries.config.grace_period.name").getString() + ": "
                            + (ConfigSettings.GRACE_ENABLED.get() ? ON : OFF)));
                },
                true, false, false, Component.translatable("arcanemysteries.config.grace_period.desc"));

        // Grace Period Length
        this.addDecimalInput("grace_length", Side.LEFT, Component.translatable("arcanemysteries.config.grace_period_length.name"),
                value -> ConfigSettings.GRACE_LENGTH.set(value.intValue()),
                input -> input.setValue(ConfigSettings.GRACE_LENGTH.get() + ""),
                true, false, false, Component.translatable("arcanemysteries.config.grace_period_length.desc_1"),
                Component.translatable("arcanemysteries.config.grace_period_length.desc_2").withStyle(ChatFormatting.DARK_GRAY));

        // Freezing Hearts Percentage
        this.addSliderButton("freezing_hearts", Side.RIGHT, Component.translatable("arcanemysteries.config.cold_freezing_hearts.name"),
                0, 1,
                (value, button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_freezing_hearts.name"), value, true);
                    ConfigSettings.HEARTS_FREEZING_PERCENTAGE.set(value);
                },
                (button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_freezing_hearts.name"), ConfigSettings.HEARTS_FREEZING_PERCENTAGE.get(), true);
                    button.setValue(ConfigSettings.HEARTS_FREEZING_PERCENTAGE.get());
                },
                true, false,
                Component.translatable("arcanemysteries.config.cold_freezing_hearts.desc"));

        // Cold Mining Speed
        this.addSliderButton("cold_mining_speed", Side.RIGHT, Component.translatable("arcanemysteries.config.cold_mining_impairment.name"),
                0, 1,
                (value, button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_mining_impairment.name"), ConfigSettings.COLD_MINING_IMPAIRMENT.get(), true);
                    ConfigSettings.COLD_MINING_IMPAIRMENT.set(value);
                },
                (button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_mining_impairment.name"), ConfigSettings.COLD_MINING_IMPAIRMENT.get(), true);
                    button.setValue(ConfigSettings.COLD_MINING_IMPAIRMENT.get());
                },
                true, false,
                Component.translatable("arcanemysteries.config.cold_mining_impairment.desc"));

        // Cold Movement Speed
        this.addSliderButton("cold_movement_speed", Side.RIGHT, Component.translatable("arcanemysteries.config.cold_movement_slowdown.name"),
                0, 1,
                (value, button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_movement_slowdown.name"), ConfigSettings.COLD_MOVEMENT_SLOWDOWN.get(), true);
                    ConfigSettings.COLD_MOVEMENT_SLOWDOWN.set(value);
                },
                (button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_movement_slowdown.name"), ConfigSettings.COLD_MOVEMENT_SLOWDOWN.get(), true);
                    button.setValue(ConfigSettings.COLD_MOVEMENT_SLOWDOWN.get());
                },
                true, false,
                Component.translatable("arcanemysteries.config.cold_movement_slowdown.desc"));

        // Cold Knockback Reduction
        this.addSliderButton("cold_knockback_reduction", Side.RIGHT, Component.translatable("arcanemysteries.config.cold_knockback_reduction.name"),
                0, 1,
                (value, button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_knockback_reduction.name"), ConfigSettings.COLD_KNOCKBACK_REDUCTION.get(), true);
                    ConfigSettings.COLD_KNOCKBACK_REDUCTION.set(value);
                },
                (button) ->
                {
                    button.setMessagePercentage(Component.translatable("arcanemysteries.config.cold_knockback_reduction.name"), ConfigSettings.COLD_KNOCKBACK_REDUCTION.get(), true);
                    button.setValue(ConfigSettings.COLD_KNOCKBACK_REDUCTION.get());
                },
                true, false,
                Component.translatable("arcanemysteries.config.cold_knockback_reduction.desc"));

        // Heat Fog Distance
        this.addSliderButton("heat_fog_distance", Side.RIGHT, Component.translatable("arcanemysteries.config.heat_fog_distance.name"),
                0, 1,
                (value, button) ->
                {
                    button.setMessage(Component.translatable("arcanemysteries.config.heat_fog_distance.name")
                            .append(": ")
                            .append(value < 1 ? Component.literal((int) (value * 64) + "")
                                    : Component.literal(OFF)));
                    ConfigSettings.HEATSTROKE_FOG_DISTANCE.set(value * 64);
                },
                (button) ->
                {
                    double value = ConfigSettings.HEATSTROKE_FOG_DISTANCE.get();
                    button.setMessage(Component.translatable("arcanemysteries.config.heat_fog_distance.name")
                            .append(": ")
                            .append(value / 64 < 1 ? Component.literal((int) value + "")
                                    : Component.literal(OFF)));
                    button.setValue(value / 64);
                },
                true, false,
                Component.translatable("arcanemysteries.config.heat_fog_distance.desc"));
    }

    @Override
    public void onClose()
    {   super.onClose();
        ConfigScreen.saveConfig();
    }
}
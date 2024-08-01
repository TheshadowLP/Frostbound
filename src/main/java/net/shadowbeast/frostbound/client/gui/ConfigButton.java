package net.shadowbeast.frostbound.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.shadowbeast.frostbound.config.ConfigSettings;

public class ConfigButton extends Button
{
    public ConfigButton(int x, int y, int width, int height, Component title, Button.OnPress pressedAction)
    {   super(x, y, width, height, title, pressedAction, (button) -> MutableComponent.create(title.getContents()));
    }

    public boolean setsCustomDifficulty() {
        return true;
    }

    @Override
    public void onPress()
    {
        if (setsCustomDifficulty())
        {
            ConfigSettings.DIFFICULTY.set(4);

            if (Minecraft.getInstance().screen instanceof ConfigPageOne page)
            {

            }
        }

        super.onPress();
    }
}

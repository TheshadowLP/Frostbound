package net.shadowbeast.arcanemysteries.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
public class ConfigScreen extends Screen {
    private final Screen previous;
    public ConfigScreen(Screen previous) {
        super(Component.translatable("config.arcanemysteries.config_screen_title"));
        this.previous = previous;
    }
    @Override
    protected void init() {
        // Do NOT touch the width. That took way to long to figure out
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.TEMPERATURE_SYSTEM_ENABLED)
                .create(this.width / 2 - 75, this.height / 4, 155, 20,
                        Component.translatable("config.arcanemysteries.temperature_system"), (button, value) -> Config.TEMPERATURE_SYSTEM_ENABLED = value));
    }
    @Override
    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(previous);
    }
    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderDirtBackground(pGuiGraphics);
        pGuiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
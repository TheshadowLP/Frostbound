package net.shadowbeast.projectshadow.config;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class ConfigScreen extends Screen {
    private final Screen previous;

    public ConfigScreen(Screen previous) {
        super(Component.literal("Project Shadow Configuration"));
        this.previous = previous;
    }

    @Override
    protected void init() {
        // Do NOT touch the width. That took way to long to figure out
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.bakedPotatoesDoDamage)
                .create(this.width / 2 - 75, this.height / 4, 155, 20,
                        Component.literal("Baked Potatoes Do Damage"), (button, value) -> Config.bakedPotatoesDoDamage = value));
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
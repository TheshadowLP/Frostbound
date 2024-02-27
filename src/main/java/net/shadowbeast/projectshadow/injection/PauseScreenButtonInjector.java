package net.shadowbeast.projectshadow.injection;

import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class PauseScreenButtonInjector {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(PauseScreenButtonInjector.class);
    }

    @SubscribeEvent
    public static void onInitGuiPost(@NotNull ScreenEvent event) {
        if (event.getScreen().isPauseScreen()) {

        }
    }
}

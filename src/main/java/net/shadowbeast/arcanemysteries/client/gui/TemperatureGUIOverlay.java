package net.shadowbeast.arcanemysteries.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import net.shadowbeast.arcanemysteries.util.GuiRenderer;
import net.shadowbeast.arcanemysteries.util.ScreenHelper;
import org.jetbrains.annotations.NotNull;

public class TemperatureGUIOverlay
{
    public static void renderTemperatureOverlay(Gui gui, ScreenHelper.ScreenOffset position, Player player, GuiRenderer renderer, boolean forgeOverlay) {
        Minecraft minecraft = Minecraft.getInstance();
        int x = ScreenHelper.getXOffset(position, minecraft) + 80;
        int y = ScreenHelper.getYOffset(position, minecraft) + 90;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        double displayTemp = EStats.getTemperatureStats(player).getDisplayTemperature();
        String temperatureText = EStats.getTemperatureStats(player).getCelsius() + " °C";

        // Log the temperature being displayed
        ArcaneMysteries.LOGGER.info("Displaying temperature: {} °C", temperatureText);

        assert Minecraft.getInstance().gameMode != null;
        if (Minecraft.getInstance().gameMode.hasExperience()) {
            if (displayTemp >= 1) {
                renderer.drawString(temperatureText, x, y, ChatFormatting.GOLD.getColor(), false);
            } else if (displayTemp <= -1) {
                renderer.drawString(temperatureText, x, y, ChatFormatting.BLUE.getColor(), false);
            } else {
                renderer.drawString(temperatureText, x, y, ChatFormatting.GRAY.getColor(), false);
            }
        }

        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }
}

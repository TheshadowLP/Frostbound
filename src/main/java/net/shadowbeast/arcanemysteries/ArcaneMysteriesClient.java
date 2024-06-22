package net.shadowbeast.arcanemysteries;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import net.shadowbeast.arcanemysteries.util.GuiRenderer;
import net.shadowbeast.arcanemysteries.util.OverlayCollector;
import net.shadowbeast.arcanemysteries.util.ScreenHelper;
import net.shadowbeast.arcanemysteries.util.SetupClient;

public class ArcaneMysteriesClient extends SetupClient {
    public static void register(){
        ArcaneMysteries.LOGGER.info("Client Registered");
        MinecraftForge.EVENT_BUS.register(new ArcaneMysteriesClient());
    }
    @Override
    public void setupGuiOverlays(OverlayCollector collector) {
        collector.register("temperature", OverlayCollector.Order.END, (gui, renderer, width, height)->{
            Minecraft minecraft = Minecraft.getInstance();
                Player player = minecraft.player;
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.disableDepthTest();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);

                renderTemperature(gui, ScreenHelper.ScreenOffset.TOP, player, renderer, true);

        });
    }

    @Override
    public ResourceLocation getModIcon() {
        return null;
    }

    public static void renderTemperature(Gui gui, ScreenHelper.ScreenOffset position, Player playerEntity, GuiRenderer renderer, boolean forgeOverlay) {
        Minecraft minecraft = Minecraft.getInstance();
        int x = ScreenHelper.getXOffset(position, minecraft) + 80;
        int y = ScreenHelper.getYOffset(position, minecraft) + 90;
        Minecraft.getInstance().getProfiler().push("temperature");
        double displayTemp = EStats.getTemperatureStats(playerEntity).getDisplayTemperature();
        //For Numbers
        String s = EStats.getTemperatureStats(playerEntity).getCelsius() + " °C";
        assert Minecraft.getInstance().gameMode != null;
        if (Minecraft.getInstance().gameMode.hasExperience()) {
                if (displayTemp >= 1) {
                    renderer.drawString(s, x, y, ChatFormatting.GOLD.getColor(), false);
                } else if (displayTemp <= -1) {
                    renderer.drawString(s, x, y, ChatFormatting.BLUE.getColor(), false);
                } else {
                    renderer.drawString(s, x, y, ChatFormatting.GRAY.getColor(), false);
                }
            }
        Minecraft.getInstance().getProfiler().pop();
    }
}
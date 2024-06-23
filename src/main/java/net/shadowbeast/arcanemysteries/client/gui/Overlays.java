package net.shadowbeast.arcanemysteries.client.gui;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.config.ConfigSettings;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.temprature.caps.TemperatureCap;
import net.shadowbeast.arcanemysteries.temprature.util.EntityTempManager;
import net.shadowbeast.arcanemysteries.util.MathHelper;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Overlays
{
    public static final ResourceLocation BODY_TEMP_GAUGE = new ResourceLocation("arcanemysteries:textures/gui/overlay/body_temp_gauge.png");
    public static final ResourceLocation BODY_TEMP_GAUGE_HC = new ResourceLocation("arcanemysteries:textures/gui/overlay/body_temp_gauge_hc.png");
    public static final ResourceLocation WORLD_TEMP_GAUGE = new ResourceLocation("arcanemysteries:textures/gui/overlay/world_temp_gauge.png");
    public static final ResourceLocation WORLD_TEMP_GAUGE_HC = new ResourceLocation("arcanemysteries:textures/gui/overlay/world_temp_gauge_hc.png");
    public static final ResourceLocation VAGUE_TEMP_GAUGE = new ResourceLocation("arcanemysteries:textures/gui/overlay/vague_temp_gauge.png");
    public static final ResourceLocation VAGUE_TEMP_GAUGE_HC = new ResourceLocation("arcanemysteries:textures/gui/overlay/vague_temp_gauge_hc.png");

    public static final ResourceLocation BAR_TEXTURE_1 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_1.png");
    public static final ResourceLocation BAR_TEXTURE_2 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_2.png");
    public static final ResourceLocation BAR_TEXTURE_3 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_3.png");
    public static final ResourceLocation BAR_TEXTURE_4 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_4.png");
    public static final ResourceLocation BAR_TEXTURE_5 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_5.png");
    public static final ResourceLocation BAR_TEXTURE_6 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_6.png");
    public static final ResourceLocation BAR_TEXTURE_7 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_7.png");
    public static final ResourceLocation BAR_TEXTURE_8 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_8.png");
    public static final ResourceLocation BAR_TEXTURE_9 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_9.png");
    public static final ResourceLocation BAR_TEXTURE_10 = new ResourceLocation("arcanemysteries:textures/gui/overlay/bar_texture_10.png");


    public static final Supplier<ResourceLocation> BODY_TEMP_GAUGE_LOCATION  = () ->
            ConfigSettings.HIGH_CONTRAST.get() ? BODY_TEMP_GAUGE_HC
                    : BODY_TEMP_GAUGE;
    public static final Supplier<ResourceLocation> WORLD_TEMP_GAUGE_LOCATION = () ->
            ConfigSettings.HIGH_CONTRAST.get() ? WORLD_TEMP_GAUGE_HC
                    : WORLD_TEMP_GAUGE;
    public static final Supplier<ResourceLocation> VAGUE_TEMP_GAUGE_LOCATION = () ->
            ConfigSettings.HIGH_CONTRAST.get() ? VAGUE_TEMP_GAUGE_HC
                    : VAGUE_TEMP_GAUGE;

    // Stuff for world temperature
    public static double WORLD_TEMP = 0;
    public static boolean ADVANCED_WORLD_TEMP = false;
    public static double PREV_WORLD_TEMP = 0;
    public static double MAX_TEMP = 0;
    public static double MIN_TEMP = 0;

    // Stuff for body temperature
    public static double BODY_TEMP = 0;
    static double PREV_BODY_TEMP = 0;
    public static int BLEND_BODY_TEMP = 0;
    public static int ICON_BOB = 0;
    public static int BODY_ICON = 0;
    public static int PREV_BODY_ICON = 0;
    public static double BODY_TEMP_SEVERITY = 0;

    public static IGuiOverlay WORLD_TEMP_OVERLAY = (gui, graphics, partialTick, width, height) -> {
        PoseStack poseStack = graphics.pose();
        Font font = Minecraft.getInstance().font;
        LocalPlayer player = Minecraft.getInstance().player;

        if (player != null && ADVANCED_WORLD_TEMP) {
            // Determine which bar texture to use based on temperature severity
            ResourceLocation barTexture = determineBarTexture(WORLD_TEMP, MIN_TEMP, MAX_TEMP);

            // Get the temperature severity
            int severity = getWorldSeverity(WORLD_TEMP, MIN_TEMP, MAX_TEMP);

            if (severity >= 0 && severity < 10) {
                gui.setupOverlayRenderState(true, false);

                poseStack.pushPose();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);

                // Render frame
                graphics.blit(barTexture,
                        (width / 2) + 92 + ConfigSettings.WORLD_GAUGE_POS.get().x(),
                        height - 19 + ConfigSettings.WORLD_GAUGE_POS.get().y(), 0, 64 - severity * 16, 25, 16, 25, 144);

                RenderSystem.disableBlend();

                // Render text
                String tempText = String.format("%.1f°C", WORLD_TEMP); // Example format
                int textWidth = font.width(tempText);
                int textX = (width / 2) + 105 - textWidth + ConfigSettings.WORLD_GAUGE_POS.get().x();
                int textY = height - 15 + ConfigSettings.WORLD_GAUGE_POS.get().y();
                //font.draw(graphics, tempText, textX, textY, 0xFFFFFF); // White color

                poseStack.popPose();
            }
        }
    };

    private static ResourceLocation determineBarTexture(double temp, double min, double max) {
        // Example logic: Select bar texture based on severity
        int severity = getWorldSeverity(temp, min, max);
        return switch (severity) {
            case 0 -> BAR_TEXTURE_1;
            case 1 -> BAR_TEXTURE_2;
            case 2 -> BAR_TEXTURE_3;
            case 3 -> BAR_TEXTURE_4;
            case 4 -> BAR_TEXTURE_5;
            case 5 -> BAR_TEXTURE_6;
            case 6 -> BAR_TEXTURE_7;
            case 7 -> BAR_TEXTURE_8;
            case 8 -> BAR_TEXTURE_9;
            case 9 -> BAR_TEXTURE_10;
            default -> BAR_TEXTURE_1;
        };
    }


    public static IGuiOverlay BODY_TEMP_OVERLAY = (gui, graphics, partialTick, width, height) ->
    {
        gui.setupOverlayRenderState(true, false);
        Minecraft mc = Minecraft.getInstance();

        if (gui.shouldDrawSurvivalElements() && !Minecraft.getInstance().options.hideGui)
        {
            // Blend body temperature (per frame)
            BLEND_BODY_TEMP = (int) MathHelper.blend(PREV_BODY_TEMP, BODY_TEMP, Minecraft.getInstance().getFrameTime(), 0, 1);

            // Get text color
            int color = switch (((int) BODY_TEMP_SEVERITY))
            {   case  7, -7 -> 16777215;
                case  6 -> 16777132;
                case  5 -> 16767856;
                case  4 -> 16759634;
                case  3 -> 16751174;
                case -3 -> 6078975;
                case -4 -> 7528447;
                case -5 -> 8713471;
                case -6 -> 11599871;
                default -> BLEND_BODY_TEMP > 0 ? 16744509
                        : BLEND_BODY_TEMP < 0 ? 4233468
                        : 11513775;
            };

            // Get the outer border color when readout is > 100
            int colorBG = BLEND_BODY_TEMP < 0 ? 1122643
                    : BLEND_BODY_TEMP > 0 ? 5376516
                    : 0;

            int bobLevel = Math.min(Math.abs(((int) BODY_TEMP_SEVERITY)), 3);
            int threatOffset = !ConfigSettings.ICON_BOBBING.get() ? 0
                    : bobLevel == 2 ? ICON_BOB
                    : bobLevel == 3 ? Minecraft.getInstance().cameraEntity.tickCount % 2
                    : 0;

            RenderSystem.defaultBlendFunc();

            // Render old icon (if blending)
            if (ConfigSettings.BODY_ICON_ENABLED.get())
            {
                int icon = Math.abs(BLEND_BODY_TEMP) < 100 ?  MathHelper.floor(BODY_TEMP_SEVERITY) : 4 * MathHelper.sign(BODY_TEMP_SEVERITY);
                int iconX = (width / 2) - 5 + ConfigSettings.BODY_ICON_POS.get().x();
                int iconYOffset = ADVANCED_WORLD_TEMP && ConfigSettings.MOVE_BODY_ICON_WHEN_ADVANCED.get()
                        ? 54
                        : 47;
                int iconY = height - iconYOffset - threatOffset + ConfigSettings.BODY_ICON_POS.get().y();
                // Render icon
                graphics.blit(BODY_TEMP_GAUGE_LOCATION.get(), iconX, iconY, 0, 40 - icon * 10, 10, 10, 10, 90);

                // Render new icon if temperature changing
                if (MathHelper.betweenExclusive(Math.abs(BLEND_BODY_TEMP), 0, 100))
                {
                    // render new icon over old icon
                    double blend = MathHelper.blend(1, 9, Math.abs(BODY_TEMP_SEVERITY), Math.abs(MathHelper.floor(BODY_TEMP_SEVERITY)), Math.abs(MathHelper.ceil(BODY_TEMP_SEVERITY)));
                    graphics.blit(BODY_TEMP_GAUGE_LOCATION.get(),
                            iconX, iconY + 10 - MathHelper.ceil(blend), 0,
                            // UV Y-coordinate for the icon in this stage
                            40 - MathHelper.grow(icon, BLEND_BODY_TEMP > 0 ? 0 : 2) * 10 - MathHelper.ceil(blend),
                            10, MathHelper.ceil(blend), 10, 90);
                }
            }

            // Render Readout
            if (ConfigSettings.BODY_READOUT_ENABLED.get() && ADVANCED_WORLD_TEMP)
            {
                Font font = mc.font;
                int scaledWidth = mc.getWindow().getGuiScaledWidth();
                int scaledHeight = mc.getWindow().getGuiScaledHeight();

                String s = "" + Math.min(Math.abs(BLEND_BODY_TEMP), 100);
                int x = (scaledWidth - font.width(s)) / 2 + ConfigSettings.BODY_READOUT_POS.get().x();
                int y = scaledHeight - 31 - 10 + ConfigSettings.BODY_READOUT_POS.get().y();

                // Draw the outline
                graphics.drawString(font, s, x + 1, y, colorBG, false);
                graphics.drawString(font, s, x - 1, y, colorBG, false);
                graphics.drawString(font, s, x, y + 1, colorBG, false);
                graphics.drawString(font, s, x, y - 1, colorBG, false);

                // Draw the readout
                graphics.drawString(font, s, x, y, color, false);
            }
        }
    };

    public static IGuiOverlay VAGUE_TEMP_OVERLAY = (gui, graphics, partialTick, width, height) ->
    {
        PoseStack poseStack = graphics.pose();
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null && !ADVANCED_WORLD_TEMP && mc.gameMode.getPlayerMode() != GameType.SPECTATOR
                && !mc.options.hideGui && ConfigSettings.WORLD_GAUGE_ENABLED.get() && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);

            // Get player world temperature
            double temp = Temperature.convert(WORLD_TEMP, ConfigSettings.CELSIUS.get() ? Temperature.Units.C : Temperature.Units.F, Temperature.Units.MC, true);
            // Get the temperature severity
            int severity = getWorldSeverity(temp, MIN_TEMP, MAX_TEMP);
            int renderOffset = MathHelper.clamp(severity, -1, 1) * 2;

            poseStack.pushPose();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            int bobLevel = Math.min(Math.abs(((int) BODY_TEMP_SEVERITY)), 3);
            int threatOffset = !ConfigSettings.ICON_BOBBING.get()
                    ? 0
                    : bobLevel == 2
                    ? ICON_BOB
                    : bobLevel == 3
                    ? Minecraft.getInstance().cameraEntity.tickCount % 2
                    : 0;

            // Render frame
            graphics.blit(VAGUE_TEMP_GAUGE_LOCATION.get(),
                    (width / 2) - 8 + ConfigSettings.BODY_ICON_POS.get().x(),
                    height - 50 + ConfigSettings.BODY_ICON_POS.get().y() - renderOffset - threatOffset,
                    0, 64 - severity * 16, 16, 16, 16, 144);

            RenderSystem.disableBlend();
            poseStack.popPose();
        }
    };

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event)
    {   event.registerBelow(VanillaGuiOverlay.CHAT_PANEL.id(), "world_temp", WORLD_TEMP_OVERLAY);
        event.registerBelow(VanillaGuiOverlay.CHAT_PANEL.id(), "body_temp", BODY_TEMP_OVERLAY);
        event.registerBelow(VanillaGuiOverlay.CHAT_PANEL.id(), "vague_temp", VAGUE_TEMP_OVERLAY);
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT)
    public static final class TickOverlays
    {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event)
        {
            Player player = Minecraft.getInstance().player;
            if (event.phase == TickEvent.Phase.START && player != null)
            {
                EntityTempManager.getTemperatureCap(player).ifPresent(icap ->
                {
                    if (!(icap instanceof TemperatureCap cap)) return;

                    cap.calculateHudVisibility(player);
                    ADVANCED_WORLD_TEMP = cap.showAdvancedWorldTemp();

                    /* World Temp */

                    // Get temperature in actual degrees
                    boolean celsius = ConfigSettings.CELSIUS.get();
                    double worldTemp = cap.getTrait(Temperature.Trait.WORLD);
                    double realTemp = Temperature.convert(worldTemp, Temperature.Units.MC, celsius ? Temperature.Units.C : Temperature.Units.F, true);
                    // Calculate the blended world temperature for this tick
                    double diff = realTemp - WORLD_TEMP;
                    PREV_WORLD_TEMP = WORLD_TEMP;
                    WORLD_TEMP += Math.abs(diff) <= 1 ? diff : MathHelper.maxAbs(diff / ConfigSettings.TEMP_SMOOTHING.get(), 0.25 * MathHelper.sign(diff));

                    // Update max/min offset
                    MAX_TEMP = cap.getTrait(Temperature.Trait.BURNING_POINT);
                    MIN_TEMP = cap.getTrait(Temperature.Trait.FREEZING_POINT);


                    /* Body Temp */

                    // Blend body temp (per tick)
                    PREV_BODY_TEMP = BODY_TEMP;
                    double currentTemp = cap.getTrait(Temperature.Trait.BODY);
                    BODY_TEMP = Math.abs(currentTemp - BODY_TEMP) < 0.1 ? currentTemp : BODY_TEMP + (cap.getTrait(Temperature.Trait.BODY) - BODY_TEMP) / 5;

                    // Handle effects for the icon (bobbing, stage, transition)
                    // Get icon bob
                    ICON_BOB = player.tickCount % 3 == 0 && Math.random() < 0.3 ? 1 : 0;

                    // Get the severity of the player's body temperature
                    BODY_TEMP_SEVERITY = getBodySeverity(BLEND_BODY_TEMP);

                    // Get the icon to be displayed
                    int neededIcon = ((int) MathHelper.clamp(BODY_TEMP_SEVERITY, -4, 4));

                    // Start transition
                    if (BODY_ICON != neededIcon)
                    {   BODY_ICON = neededIcon;
                    }
                });
            }
        }
    }

    public static int getWorldSeverity(double temp, double min, double max)
    {   return (int) MathHelper.blend(-4, 4, temp, min, max);
    }

    static double getBodySeverity(int temp)
    {   int sign = MathHelper.sign(temp);
        int absTemp = Math.abs(temp);

        return absTemp < 100 ? MathHelper.blend(0d, 3d, absTemp, 0, 100) * sign
                : MathHelper.blend(3d, 7d, absTemp, 100, 150) * sign;
    }

    public static void setBodyTempInstant(double temp)
    {   BODY_TEMP = temp;
        PREV_BODY_TEMP = temp;
        BLEND_BODY_TEMP = (int) temp;
    }

    public static void setWorldTempInstant(double temp)
    {   WORLD_TEMP = temp;
        PREV_WORLD_TEMP = temp;
    }
}

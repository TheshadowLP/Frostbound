package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.client.gui.Overlays;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class XPBarMixin
{
    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void renderExperienceBar(GuiGraphics guiGraphics, int x, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            // Calculate the color based on body temperature severity
            int color = calculateColorBasedOnTemp();

            // Render the experience bar with the new color
            renderCustomExperienceBar(guiGraphics, x, color);
            ci.cancel();
        }
    }

    private int calculateColorBasedOnTemp() {
        int tempSeverity = (int) Overlays.BODY_TEMP_SEVERITY;
        return switch (tempSeverity) {
            case -7, 7 -> 0xFFFFFF; // White
            case 6 -> 0xFFD700; // Gold
            case 5 -> 0xFFA500; // Orange
            case 4 -> 0xFF4500; // OrangeRed
            case 3 -> 0xFF0000; // Red
            case -3 -> 0x5F9EA0; // CadetBlue
            case -4 -> 0x4682B4; // SteelBlue
            case -5 -> 0x87CEEB; // SkyBlue
            case -6 -> 0x00BFFF; // DeepSkyBlue
            default -> Overlays.BLEND_BODY_TEMP > 0 ? 0xDC143C // Crimson
                    : Overlays.BLEND_BODY_TEMP < 0 ? 0x1E90FF // DodgerBlue
                    : 0xADD8E6; // LightBlue
        };
    }

    private void renderCustomExperienceBar(GuiGraphics guiGraphics, int x, int color) {
        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        int barWidth = (int) (mc.player.experienceProgress * (width / 2 + 91));

        guiGraphics.fill(x, width - 32, x + barWidth, width - 31, color);
    }

}

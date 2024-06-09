package net.shadowbeast.arcanemysteries.temprature;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class TempratureHudOverlay {
    private static final ResourceLocation FILLED_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/full_temp_meter.png");
    private static final ResourceLocation ONE_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/1_temp_meter.png");
    private static final ResourceLocation TWO_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/2_temp_meter.png");
    private static final ResourceLocation THREE_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/3_temp_meter.png");
    private static final ResourceLocation FOUR_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/4_temp_meter.png");
    private static final ResourceLocation FIVE_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/5_temp_meter.png");
    private static final ResourceLocation EMPTY_TEMPRATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/empty_temp_meter.png");
    private static final ResourceLocation FULL_TEMPRATURE_NO_GLASS = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temprature/full_temp_meter_no_glass.png");

    public static final IGuiOverlay HUD_TEMPRATURE = ((gui, poseStack, partialTick, width, height) -> {
        int x = width  - 350;
        int y = height - 34;
        poseStack.blit(EMPTY_TEMPRATURE, x, y,0,0,16,32,
                16,32);
        poseStack.blit(FULL_TEMPRATURE_NO_GLASS, x, y,0,0,16,5,
                16,5);

//        RenderSystem.setShaderTexture(0, EMPTY_TEMPRATURE);
//        for (int i = 0; i < 10; i++) {
//            if (ClientTemperatureData.getPlayerTemperature() > i) {
//                poseStack.blit(EMPTY_TEMPRATURE,x + (i * 1),y,0,0,12,12,
//                        16,32);
//            } else {
//                break;
//            }
//        }
    });
}

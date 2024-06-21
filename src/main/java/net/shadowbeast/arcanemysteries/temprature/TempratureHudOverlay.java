package net.shadowbeast.arcanemysteries.temprature;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class TempratureHudOverlay {
    private static final ResourceLocation FILLED_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/full_temp_meter.png");
    private static final ResourceLocation ONE_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/1_temp_meter.png");
    private static final ResourceLocation TWO_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/2_temp_meter.png");
    private static final ResourceLocation THREE_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/3_temp_meter.png");
    private static final ResourceLocation FOUR_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/4_temp_meter.png");
    private static final ResourceLocation FIVE_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/5_temp_meter.png");
    private static final ResourceLocation EMPTY_TEMPERATURE = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/empty_temp_meter.png");
    private static final ResourceLocation FULL_TEMPERATURE_NO_GLASS = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/temperature/full_temp_meter_no_glass.png");

    public static final IGuiOverlay HUD_TEMPERATURE = ((gui, poseStack, partialTick, width, height) -> {
        int x = width  - 350;
        int y = height - 34;
        poseStack.blit(EMPTY_TEMPERATURE, x, y,0,0,16,32,
                16,32);
        poseStack.blit(FULL_TEMPERATURE_NO_GLASS, x, y, 0, 5, 16, 32,
                16, 32);

//        RenderSystem.setShaderTexture(0, EMPTY_TEMPERATURE);
//        for (int i = 0; i < 10; i++) {
//            if (ClientTemperatureData.getPlayerTemperature() > i) {
//                poseStack.blit(EMPTY_TEMPERATURE,x + (i * 1),y,0,0,12,12,
//                        16,32);
//            } else {
//                break;
//            }
//        }
    });
}

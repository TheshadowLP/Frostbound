package net.shadowbeast.arcanemysteries.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin
{


    @Shadow public Minecraft minecraft;
    @Shadow @Final public RandomSource random;
    @Shadow public Player getCameraPlayer() {return null;}
    @Shadow public void renderTextureOverlay(GuiGraphics p_282304_, ResourceLocation p_168709_, float p_168710_) {}
    @Shadow public int screenWidth;
    @Shadow public int screenHeight;
    @Shadow protected int tickCount;


    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
    public void hotbarColor(float p_283031_, GuiGraphics guiGraphics, CallbackInfo ci) {
        Player playerentity = this.getCameraPlayer();

        double displayTemp = EStats.getTemperatureStats(playerentity).getDisplayTemperature();

        float heatTemp = (float) (1.0F - displayTemp);
        float coldTemp = (float) (1.0F + displayTemp);
        float whiteTemp = (float) ((1.0F - Math.abs(displayTemp))/2 + 0.5F);
        guiGraphics.setColor(coldTemp, whiteTemp, heatTemp, 1.0F);

    }


    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"))
    public void resetHotbarColor(float p_283031_, GuiGraphics guiGraphics, CallbackInfo ci) {
        guiGraphics.setColor(1f, 1f, 1f, 1f);
    }
    public Minecraft getMinecraftInstance() {
        return minecraft;
    }

}

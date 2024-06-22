package net.shadowbeast.arcanemysteries.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Final
    @Shadow
    protected Minecraft minecraft;

    @Shadow @Final
    protected RandomSource random;

    @Contract(pure = true)
    @Shadow
    private @Nullable Player getCameraPlayer() {return null;}

    @Shadow
    protected void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation resourceLocation, float alpha) {}

    @Shadow
    protected int screenWidth;

    @Shadow
    protected int screenHeight;

    @Shadow protected int tickCount;


    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
    public void hotbarColor(float partialTick, @NotNull GuiGraphics guiGraphics, CallbackInfo ci) {
        Player playerEntity = this.getCameraPlayer();

        double displayTemp = EStats.getTemperatureStats(playerEntity).getDisplayTemperature();

        float heatTemp = (float) (1.0F - displayTemp);
        float coldTemp = (float) (1.0F + displayTemp);
        float whiteTemp = (float) ((1.0F - Math.abs(displayTemp))/2 + 0.5F);
        ArcaneMysteries.LOGGER.info(displayTemp);
        guiGraphics.setColor(coldTemp, whiteTemp, heatTemp, 1.0F);

    }

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"))
    public void resetHotbarColor(float partialTick, @NotNull GuiGraphics guiGraphics, CallbackInfo ci) {
        guiGraphics.setColor(1f, 1f, 1f, 1f);
    }

    @Unique
    public Minecraft arcaneMysteries$getMinecraftInstance() { return minecraft; }

}

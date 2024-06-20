package net.shadowbeast.arcanemysteries.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface GuiRenderer {
    GuiGraphics guiGraphics();

    PoseStack poseStack();

    float partialTicks();

    void blit(ResourceLocation paramResourceLocation, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);

    void blit(ResourceLocation paramResourceLocation, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);

    void drawString(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

    void drawString(Component paramComponent, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

    void renderSelectionList(AbstractSelectionList<?> paramAbstractSelectionList, int paramInt1, int paramInt2);

    void fillOverlay(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);

    void fillGradient(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);

    void fillGradient(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
}

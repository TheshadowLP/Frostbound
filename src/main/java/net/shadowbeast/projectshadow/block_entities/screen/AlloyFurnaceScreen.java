package net.shadowbeast.projectshadow.block_entities.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.block_entities.menu.AlloyFurnaceMenu;
import org.jetbrains.annotations.NotNull;

public class AlloyFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ProjectShadow.MOD_ID, "textures/gui/fusion_furnace_gui.png");

    public AlloyFurnaceScreen(AlloyFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(@NotNull GuiGraphics pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (!menu.hasFuelInSlot()) {
            guiGraphics.blit(TEXTURE, x + 82, y + 28, 176, 33, 13, 13);
        }
        int k = menu.getScaledFuel();
        guiGraphics.blit(TEXTURE, x + 81, y + 10 + 12 - k, 176, 12 - k, 14, k + 1);
        if (menu.isCrafting()) {
            if (menu.getScaledProgress() < 10) {
                guiGraphics.blit(TEXTURE, x + 67, y + 40, 176, 14, 42, menu.getScaledProgress());
            } else if (menu.getScaledProgress() < 24) {
                guiGraphics.blit(TEXTURE, x + 67, y + 40, 176, 14, 42, 9);
                guiGraphics.blit(TEXTURE, x + 70, y + 46, 176, 23, menu.getScaledProgress() - 9, 3);
                guiGraphics.blit(TEXTURE, x + 106 - (menu.getScaledProgress() - 9), y + 46, 212 - (menu.getScaledProgress() - 9), 23, (menu.getScaledProgress() - 9), 3);
            } else {
                guiGraphics.blit(TEXTURE, x + 67, y + 40, 176, 14, 42, 9);
                guiGraphics.blit(TEXTURE, x + 70, y + 46, 176, 23, menu.getScaledProgress() - 9, 3);
                guiGraphics.blit(TEXTURE, x + 106 - 15, y + 46, 212 - 15, 23, 15, 3);
                guiGraphics.blit(TEXTURE, x + 85, y + 46, 176, 27, 42, menu.getScaledProgress() - 24);
            }
        }
    }
}


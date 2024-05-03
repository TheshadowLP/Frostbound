package net.shadowbeast.projectshadow.block_entities.screen;

import com.mojang.blaze3d.systems.RenderSystem;
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

        if(!menu.hasFuelInSlot()) {
            guiGraphics.blit(TEXTURE, x + 82, y + 30, 176, 32, 13, 13);
        }
        if (menu.isCrafting()) {
            if(menu.getScaledProgress() < 10) {
                guiGraphics.blit(TEXTURE, x + 67, y + 42, 176, 14, 42, menu.getScaledProgress());
            } else if (menu.getScaledProgress() < 21) {
                guiGraphics.blit(TEXTURE, x + 67, y + 42, 176, 14, 42, 8);
                guiGraphics.blit(TEXTURE, x + 70, y + 48, 176, 23, menu.getScaledProgress() - 9, 3);
            } else {
                guiGraphics.blit(TEXTURE, x + 67, y + 42, 176, 14, 42, 8);
                guiGraphics.blit(TEXTURE, x + 70, y + 48, 176, 23, 15, 3);
                guiGraphics.blit(TEXTURE, x + 85, y + 48, 176, 26, 42, menu.getScaledProgress() - 21);
            }
            guiGraphics.blit(TEXTURE, x + 81, y + 12, 176, 0, 14, menu.getScaledFuel());
        }
    }
}


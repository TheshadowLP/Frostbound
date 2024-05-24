package net.shadowbeast.arcanemysteries.block_entities.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.block_entities.menu.AlloyFurnaceMenu;
import org.jetbrains.annotations.NotNull;

public class AlloyFurnaceScreen extends AbstractContainerScreen<AlloyFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ArcaneMysteries.MODID, "textures/gui/fusion_furnace_gui.png");
    private static final int FIRST_PART_X = 67;
    private static final int FIRST_PART_Y = 40;
    private static final int SECOND_PART_RIGHT_X = 70;
    private static final int SECOND_PART_Y = 46;
    public static final int SECOND_PART_LEFT_X = 106;
    public static final int THIRD_PART_X = 85;
    public static final int THIRD_PART_Y = 46;

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

        // Background
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // Fuel slot
        if (!menu.hasFuelInSlot()) {
            guiGraphics.blit(TEXTURE, x + 82, y + 28, 176, 33, 13, 13);
        }
        // Fire
        int k = menu.getScaledFuel();
        guiGraphics.blit(TEXTURE, x + 80, y + 22 - k, 176, 12 - k, 14, k + 1);
        // Progress bar
        if (menu.isCrafting()) {
            if (menu.getScaledProgress() < 10) {
                guiGraphics.blit(TEXTURE, x + FIRST_PART_X, y + FIRST_PART_Y, 176, 14, 42, menu.getScaledProgress());
            } else if (menu.getScaledProgress() < 24) {
                guiGraphics.blit(TEXTURE, x + FIRST_PART_X, y + FIRST_PART_Y, 176, 14, 42, 9);
                guiGraphics.blit(TEXTURE, x + SECOND_PART_RIGHT_X, y + SECOND_PART_Y, 176, 23, menu.getScaledProgress() - 9, 3);
                guiGraphics.blit(TEXTURE, x + SECOND_PART_LEFT_X - (menu.getScaledProgress() - 9), y + SECOND_PART_Y, 212 - (menu.getScaledProgress() - 9), 23, (menu.getScaledProgress() - 9), 3);
            } else {
                guiGraphics.blit(TEXTURE, x + FIRST_PART_X, y + FIRST_PART_Y, 176, 14, 42, 9);
                guiGraphics.blit(TEXTURE, x + SECOND_PART_RIGHT_X, y + SECOND_PART_Y, 176, 23, menu.getScaledProgress() - 9, 3);
                guiGraphics.blit(TEXTURE, x + SECOND_PART_LEFT_X - 15, y + SECOND_PART_Y, 212 - 15, 23, 15, 3);
                guiGraphics.blit(TEXTURE, x + THIRD_PART_X, y + THIRD_PART_Y, 176, 26, 42, menu.getScaledProgress() - 24);
            }
        }
    }
}


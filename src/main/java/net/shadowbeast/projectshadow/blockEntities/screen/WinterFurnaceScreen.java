package net.shadowbeast.projectshadow.blockEntities.screen;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blockEntities.recipes.WinterFurnaceRecipeBookComponent;


public class WinterFurnaceScreen extends AbstractFurnaceScreen<WinterFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ProjectShadow.MOD_ID,"textures/gui/winter_furnace.png");
    public WinterFurnaceScreen(WinterFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new WinterFurnaceRecipeBookComponent(), pPlayerInventory, pTitle, TEXTURE);
    }
}

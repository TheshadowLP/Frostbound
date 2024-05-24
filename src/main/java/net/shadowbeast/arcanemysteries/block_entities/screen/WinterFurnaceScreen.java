package net.shadowbeast.arcanemysteries.block_entities.screen;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.block_entities.menu.WinterFurnaceMenu;
import net.shadowbeast.arcanemysteries.block_entities.recipes.WinterFurnaceRecipeBookComponent;

public class WinterFurnaceScreen extends AbstractFurnaceScreen<WinterFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ArcaneMysteries.MODID,"textures/gui/winter_furnace.png");
    public WinterFurnaceScreen(WinterFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new WinterFurnaceRecipeBookComponent(), pPlayerInventory, pTitle, TEXTURE);
    }
}

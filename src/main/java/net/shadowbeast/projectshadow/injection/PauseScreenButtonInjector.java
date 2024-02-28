package net.shadowbeast.projectshadow.injection;


import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.projectshadow.blocks.ModBlocks;


public class PauseScreenButtonInjector extends Button {

    private static final ItemStack ICON = new ItemStack(ModBlocks.ALLOY_FURNACE.get().asItem());

    protected PauseScreenButtonInjector(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress, CreateNarration pCreateNarration) {
        super(pX, pY, 20, 20, Component.empty(), PauseScreenButtonInjector::click, pCreateNarration);
    }

    private static void click(Button button) {
    }

}

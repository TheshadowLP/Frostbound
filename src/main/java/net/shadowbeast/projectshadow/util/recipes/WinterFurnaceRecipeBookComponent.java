package net.shadowbeast.projectshadow.util.recipes;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.shadowbeast.projectshadow.items.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class WinterFurnaceRecipeBookComponent extends AbstractFurnaceRecipeBookComponent {
    @Override
    protected @NotNull Set<Item> getFuelItems() {
        return Set.of(ModItems.STEEL_INGOT.get(), Items.COPPER_INGOT,Items.IRON_INGOT);
    }
}

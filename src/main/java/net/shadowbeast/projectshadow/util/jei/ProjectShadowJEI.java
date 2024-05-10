package net.shadowbeast.projectshadow.util.jei;

import mezz.jei.api.*;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.block_entities.menu.*;
import net.shadowbeast.projectshadow.block_entities.screen.*;
import net.shadowbeast.projectshadow.block_entities.recipes.*;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.util.jei.category.*;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ProjectShadowJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ProjectShadow.MOD_ID, "jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrushingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AlloyingCategory(registration.getJeiHelpers().getGuiHelper()));
        //TODO
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        CrusherRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        AlloyFurnaceRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        //TODO
    }
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrusherScreen.class, 82, 35, 11, 18,
                CrushingCategory.CRUSHER_RECIPE_TYPE);
        registration.addRecipeClickArea(AlloyFurnaceScreen.class, 81, 10, 14, 14,
                AlloyingCategory.ALLOY_FURNACE_RECIPE_TYPE);
    }
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER.get()), CrushingCategory.CRUSHER_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_FURNACE.get()), AlloyingCategory.ALLOY_FURNACE_RECIPE_TYPE);
    }
    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CrusherMenu.class, ModMenuTypes.CRUSHER_MENU.get(),
                CrushingCategory.CRUSHER_RECIPE_TYPE, 0, 3, 3, 36);
        registration.addRecipeTransferHandler(AlloyFurnaceMenu.class, ModMenuTypes.ALLOY_FURNACE_MENU.get(),
                AlloyingCategory.ALLOY_FURNACE_RECIPE_TYPE, 0, 3, 3, 36);
    }
}

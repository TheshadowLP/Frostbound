package net.shadowbeast.projectshadow.util.jei;

import mezz.jei.api.*;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blockEntities.screen.*;
import net.shadowbeast.projectshadow.blockEntities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.projectshadow.blockEntities.recipes.CrusherRecipe;
import net.shadowbeast.projectshadow.util.jei.category.*;
import org.jetbrains.annotations.NotNull;

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
        registration.addRecipeClickArea(AlloyFurnaceScreen.class, 83, 19, 30, 45,
                AlloyingCategory.ALLOY_FURNACE_RECIPE_TYPE); //TODO
    }
}

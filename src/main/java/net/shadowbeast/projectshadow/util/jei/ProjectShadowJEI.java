package net.shadowbeast.projectshadow.util.jei;

import mezz.jei.api.*;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blockEntities.recipes.*;
import net.shadowbeast.projectshadow.blockEntities.screen.*;
import net.shadowbeast.projectshadow.util.jei.category.*;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
@MethodsReturnNonnullByDefault
public class ProjectShadowJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ProjectShadow.MOD_ID, "jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrushingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AlloyingCategory(registration.getJeiHelpers().getGuiHelper()));
        //TODO will be avaible in the second mod version
    }
    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        CrusherRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        AlloyFurnaceRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        //TODO will be avaible in the second mod version
    }
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrusherScreen.class, 80, 17, 16, 16,
                CrushingCategory.CRUSHER_RECIPE_TYPE);
        registration.addRecipeClickArea(AlloyFurnaceScreen.class, 83, 19, 30, 45,
                AlloyingCategory.ALLOY_FURNACE_RECIPE_TYPE);
    }
}

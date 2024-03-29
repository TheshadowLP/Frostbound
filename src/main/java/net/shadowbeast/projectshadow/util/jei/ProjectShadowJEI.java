package net.shadowbeast.projectshadow.util.jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.entities.screen.CrusherScreen;
import net.shadowbeast.projectshadow.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.projectshadow.recipes.CrusherRecipe;
import net.shadowbeast.projectshadow.util.jei.category.AlloyingCategory;
import net.shadowbeast.projectshadow.util.jei.category.CrushingCategory;

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
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        CrusherRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
        AlloyFurnaceRecipe.addAllRecipes(Minecraft.getInstance().level.getRecipeManager(), registration);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrusherScreen.class, 83, 21, 30, 41,
                CrushingCategory.CRUSHER_RECIPE_TYPE);
    }
}

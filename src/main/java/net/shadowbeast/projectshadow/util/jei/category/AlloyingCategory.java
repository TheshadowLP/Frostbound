package net.shadowbeast.projectshadow.util.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.block_entities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.projectshadow.blocks.ModBlocks;

import javax.annotation.ParametersAreNonnullByDefault;


@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AlloyingCategory implements IRecipeCategory<AlloyFurnaceRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ProjectShadow.MOD_ID, "alloying");
    public static final ResourceLocation TEXTURES = new ResourceLocation(ProjectShadow.MOD_ID,
            "textures/gui/fusion_furnace_gui.png");
    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE_TYPE =
            new RecipeType<>(UID, AlloyFurnaceRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public AlloyingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURES, 0, 0, 176, 81);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ALLOY_FURNACE.get()));
    }

    @Override
    public RecipeType<AlloyFurnaceRecipe> getRecipeType() {
        return ALLOY_FURNACE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.projectshadow.alloying");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlloyFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 16).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 50).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.INPUT, 18, 50).addIngredients(recipe.getFuelItem());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 33).addItemStack(recipe.getResultItem());
    }
}

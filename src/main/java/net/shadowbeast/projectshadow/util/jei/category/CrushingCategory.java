package net.shadowbeast.projectshadow.util.jei.category;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.items.ModItems;
import net.shadowbeast.projectshadow.recipes.CrusherRecipe;
import org.jetbrains.annotations.NotNull;

@MethodsReturnNonnullByDefault
public class CrushingCategory implements IRecipeCategory<CrusherRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ProjectShadow.MOD_ID, "crushing");
    public static final ResourceLocation TEXTURES = new ResourceLocation(ProjectShadow.MOD_ID,
            "textures/gui/crusher_gui.png");
    public static final RecipeType<CrusherRecipe> CRUSHER_RECIPE_TYPE =
            new RecipeType<>(UID, CrusherRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public CrushingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURES, 0, 0,175, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHER.get()));
    }


    @Override
    public RecipeType<CrusherRecipe> getRecipeType() {
        return CRUSHER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Crushing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrusherRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 46).addIngredients(Ingredient.of(ModItems.SAW_BLADE.get()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 55).addItemStack(recipe.getResultItem());
    }
}

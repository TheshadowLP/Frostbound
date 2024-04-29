package net.shadowbeast.projectshadow.util.jei.category;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.block_entities.recipes.CrusherRecipe;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import org.jetbrains.annotations.NotNull;

@MethodsReturnNonnullByDefault
public class CrushingCategory implements IRecipeCategory<CrusherRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ProjectShadow.MOD_ID, "crushing");
    public static final ResourceLocation TEXTURES = new ResourceLocation(ProjectShadow.MOD_ID,
            "textures/gui/crusher_gui.png");
    public static final RecipeType<CrusherRecipe> CRUSHER_RECIPE_TYPE = new RecipeType<>(UID, CrusherRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private int renderProgress = 0;
    private static final int[] BUBBLELENGTHS = new int[]{29, 24, 20, 16, 11, 6, 0};
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
        return Component.translatable("jei.projectshadow.macerating");
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
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 46).addIngredients(recipe.getFuelItem());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 55).addItemStack(recipe.getResultItem());
    }
    @Override
    public void draw(@NotNull CrusherRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics pGuiGraphics, double mouseX, double mouseY) {
        pGuiGraphics.blit(TEXTURES, 0, 0, 0, 0, this.background.getWidth(), this.background.getHeight());
        if(renderProgress % 2 == 0){
            pGuiGraphics.blit(TEXTURES, 27, 40 - renderProgress, 176, 13 - renderProgress, 14, renderProgress + 1);
        } else {
            pGuiGraphics.blit(TEXTURES, 27, 40 - (renderProgress - 1), 176, 13 - (renderProgress - 1), 14, (renderProgress - 1) + 1);
        }
        int j1 = BUBBLELENGTHS[renderProgress / 2 % 7];
        if (j1 > 0) {
            pGuiGraphics.blit(TEXTURES, 81, 54 - j1, 190, 19 - j1, 13, j1);
        }
        renderProgress++;
        if (renderProgress >= 70) {
            renderProgress = 0;
        }
    }
}

package net.shadowbeast.arcanemysteries.util.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.block_entities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.arcanemysteries.blocks.ModBlocks;

import javax.annotation.ParametersAreNonnullByDefault;
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AlloyingCategory implements IRecipeCategory<AlloyFurnaceRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ArcaneMysteries.MOD_ID, "alloying");
    public static final ResourceLocation TEXTURES = new ResourceLocation(ArcaneMysteries.MOD_ID,
            "textures/gui/fusion_furnace_gui.png");
    public static final RecipeType<AlloyFurnaceRecipe> ALLOY_FURNACE_RECIPE_TYPE =
            new RecipeType<>(UID, AlloyFurnaceRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private int renderProgress = 0;
    public AlloyingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURES, 24, 7, 128, 76);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ALLOY_FURNACE.get()));
    }
    @Override
    public RecipeType<AlloyFurnaceRecipe> getRecipeType() {
        return ALLOY_FURNACE_RECIPE_TYPE;
    }
    @Override
    public Component getTitle() {
        return Component.translatable("jei.arcanemysteries.alloying");
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
        builder.addSlot(RecipeIngredientRole.INPUT, 35, 14).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 77, 14).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 19).addIngredients(recipe.getFuelItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 51).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(AlloyFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int scaledRenderProgress = renderProgress / 20;
        guiGraphics.blit(TEXTURES, 57, 3, 176, 0, 14, 14);
        if (scaledRenderProgress < 10) {
            guiGraphics.blit(TEXTURES, 43, 33, 176, 14, 42, scaledRenderProgress);
        } else if (scaledRenderProgress < 24) {
            guiGraphics.blit(TEXTURES, 43, 33, 176, 14, 42, 9);
            guiGraphics.blit(TEXTURES, 46, 39, 176, 23, scaledRenderProgress - 9, 3);
            guiGraphics.blit(TEXTURES, 82 - (scaledRenderProgress - 9), 39, 212 - (scaledRenderProgress - 9), 23, (scaledRenderProgress - 9), 3);
        } else {
            guiGraphics.blit(TEXTURES, 43, 33, 176, 14, 42, 9);
            guiGraphics.blit(TEXTURES, 46, 39, 176, 23, scaledRenderProgress - 9, 3);
            guiGraphics.blit(TEXTURES, 82 - 15, 39, 212 - 15, 23, 15, 3);
            guiGraphics.blit(TEXTURES, 61, 39, 176, 26, 42, scaledRenderProgress - 24);
        }
        renderProgress++;
        if (scaledRenderProgress >= 31) {
            renderProgress = 0;
        }
    }
}

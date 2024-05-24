package net.shadowbeast.arcanemysteries.util.jei.category;

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
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.block_entities.recipes.CrusherRecipe;
import net.shadowbeast.arcanemysteries.registries.ModBlocks;
import org.jetbrains.annotations.NotNull;

@MethodsReturnNonnullByDefault
public class CrushingCategory implements IRecipeCategory<CrusherRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(ArcaneMysteries.MODID, "crushing");
    public static final ResourceLocation TEXTURES = new ResourceLocation(ArcaneMysteries.MODID,
            "textures/gui/crusher_gui.png");
    public static final RecipeType<CrusherRecipe> CRUSHER_RECIPE_TYPE = new RecipeType<>(UID, CrusherRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private int renderProgress = 0;
    private static final int RENDER_NUMBER = 40;
    public CrushingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURES, 16, 11, 92, 68);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHER.get()));
    }
    @Override
    public RecipeType<CrusherRecipe> getRecipeType() {
        return CRUSHER_RECIPE_TYPE;
    }
    @Override
    public Component getTitle() {
        return Component.translatable("jei.arcanemysteries.macerating");
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
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 6).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 35).addIngredients(recipe.getFuelItem());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 44).addItemStack(recipe.getResultItem());
    }
    @Override
    public void draw(@NotNull CrusherRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull GuiGraphics pGuiGraphics, double mouseX, double mouseY) {
      //pGuiGraphics.blit(TEXTURES, 11, 29 - getRenderNum(), 176, 13 - getRenderNum(), 14, getRenderNum() + 1);
        pGuiGraphics.blit(TEXTURES, 65, 43 - getRenderNum(), 190, 19 - getRenderNum(), 13, getRenderNum());
        renderProgress++;
        if (renderProgress >= 400) {
            renderProgress = 0;
        }
    }
    private int getRenderNum() {
        return renderProgress / 20;
    }
}

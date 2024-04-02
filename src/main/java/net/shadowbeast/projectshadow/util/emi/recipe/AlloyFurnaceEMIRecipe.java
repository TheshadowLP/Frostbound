package net.shadowbeast.projectshadow.util.emi.recipe;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blockEntities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlloyFurnaceEMIRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;
    public static final EmiTexture TEXTURES = new EmiTexture(new ResourceLocation(ProjectShadow.MOD_ID,
            "textures/gui/fusion_furnace_gui.png"), 0, 0, 176, 81);
    public static final EmiStack ALLOY_FURNACE_WORKSHOP = EmiStack.of(ModBlocks.ALLOY_FURNACE.get());
    public static final EmiRecipeCategory ALLOYING_CATEGORY =
            new EmiRecipeCategory(new ResourceLocation(ProjectShadow.MOD_ID, "alloy_furnace"),
                    ALLOY_FURNACE_WORKSHOP, TEXTURES);

    public AlloyFurnaceEMIRecipe(AlloyFurnaceRecipe recipes) {
        this.id = recipes.getId();
        this.input = new ArrayList<>(2);
        for(Ingredient ingredient : recipes.getIngredients()) {
            this.input.add(EmiIngredient.of(ingredient));
        }
        this.output = List.of(EmiStack.of(recipes.getResultItem()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ALLOYING_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return TEXTURES.width;
    }

    @Override
    public int getDisplayHeight() {
        return TEXTURES.height;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTexture(TEXTURES, 0, 0);

        widgetHolder.addSlot(input.get(0), 66, 16);
        widgetHolder.addSlot(input.get(1), 66, 50);
        widgetHolder.addSlot(EmiIngredient.of(Ingredient.of(Items.LAVA_BUCKET)), 18, 50);

        widgetHolder.addSlot(output.get(0), 114, 33).recipeContext(this);
    }
    public static void register(EmiRegistry registry) {
        registry.addCategory(ALLOYING_CATEGORY);
        registry.addWorkstation(ALLOYING_CATEGORY, ALLOY_FURNACE_WORKSHOP);
        RecipeManager manager = registry.getRecipeManager();
        for(AlloyFurnaceRecipe recipe : manager.getAllRecipesFor(AlloyFurnaceRecipe.Type.INSTANCE)) {
            registry.addRecipe(new AlloyFurnaceEMIRecipe(recipe));
        }
    }
}

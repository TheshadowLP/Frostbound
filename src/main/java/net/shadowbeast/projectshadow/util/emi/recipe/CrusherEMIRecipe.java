package net.shadowbeast.projectshadow.util.emi.recipe;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blockEntities.recipes.CrusherRecipe;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.items.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrusherEMIRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;
    public static final EmiStack CRUSHER_WORKSHOP = EmiStack.of(ModBlocks.CRUSHER.get());
    public static final EmiTexture TEXTURES = new EmiTexture(new ResourceLocation(ProjectShadow.MOD_ID,
            "textures/gui/crusher_gui.png"), 0, 0, 175, 85);
    public static final EmiRecipeCategory CRUSHING_CATEGORY =
            new EmiRecipeCategory(new ResourceLocation(ProjectShadow.MOD_ID, "crusher"),
                    CRUSHER_WORKSHOP, TEXTURES);

    public CrusherEMIRecipe(CrusherRecipe recipe) {
        this.id = recipe.getId();
        this.input = List.of(EmiIngredient.of(recipe.getIngredients().get(0)));
        this.output = List.of(EmiStack.of(recipe.getResultItem()));
    }
    @Override
    public EmiRecipeCategory getCategory() {
        return CRUSHING_CATEGORY;
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
    public void addWidgets(WidgetHolder widget) {
        widget.addTexture(TEXTURES, 0, 0);

        widget.addSlot(input.get(0), 80, 17);
        widget.addSlot(EmiIngredient.of(Ingredient.of(ModItems.SAW_BLADE.get())), 26, 46);

        widget.addSlot(output.get(0), 80, 55).recipeContext(this);
    }
    public static void register(EmiRegistry registry) {
        registry.addCategory(CRUSHING_CATEGORY);
        registry.addWorkstation(CRUSHING_CATEGORY, CRUSHER_WORKSHOP);
        RecipeManager manager = registry.getRecipeManager();
        for(CrusherRecipe recipe : manager.getAllRecipesFor(CrusherRecipe.Type.INSTANCE)) {
            registry.addRecipe(new CrusherEMIRecipe(recipe));
        }
    }
}

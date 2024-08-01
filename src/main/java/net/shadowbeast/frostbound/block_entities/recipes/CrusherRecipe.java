package net.shadowbeast.frostbound.block_entities.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.util.ModTags;
import net.shadowbeast.frostbound.util.jei.category.CrushingCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrusherRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    public final static int DEFAULT_COOK_TIME = 200;
    public final static float DEFAULT_EXPERIENCE = 0.0f;
    private final float experience;
    protected final int crushingTime;
    public CrusherRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputItems, int crushingTime, float experience) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.crushingTime = crushingTime;
        this.experience = experience;
    }
    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        return inputItems.get(0).test(pContainer.getItem(0));
    }
    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer p_44001_, @NotNull RegistryAccess p_267165_) {
        return output.copy();
    }
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }
    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess p_267052_) {
        return output.copy();
    }
    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }
    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public ItemStack getResultItem() {
        return output.copy();
    }
    public Ingredient getFuelItem() {
        return Ingredient.of(ModTags.Items.SAW_BLADES);
    }
    public int getCrushingTime() {
        return this.crushingTime;
    }
    public static class Type implements RecipeType<CrusherRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "crushing";
    }
    public static void addAllRecipes(RecipeManager recipeManager, IRecipeRegistration registration) {
        List<CrusherRecipe> crusherRecipes = recipeManager.getAllRecipesFor(Type.INSTANCE);
        registration.addRecipes(CrushingCategory.CRUSHER_RECIPE_TYPE, crusherRecipes);
    }
    public static class Serializer implements RecipeSerializer<CrusherRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(Frostbound.MOD_ID,"crushing");
        @Override
        public @NotNull CrusherRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            int i = GsonHelper.getAsInt(json, "crushingTime", DEFAULT_COOK_TIME);
            float experience = GsonHelper.getAsFloat(json, "experience", DEFAULT_EXPERIENCE);
            return new CrusherRecipe(id, output, inputs, i, experience);
        }
        @Override
        public CrusherRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            ItemStack output = buf.readItem();
            int i = buf.readVarInt();
            float experience = buf.readFloat();
            return new CrusherRecipe(id, output, inputs, i, experience);
        }
        @Override
        public void toNetwork(FriendlyByteBuf buf, CrusherRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeVarInt(recipe.crushingTime);
            buf.writeFloat(recipe.experience);
        }
    }
}
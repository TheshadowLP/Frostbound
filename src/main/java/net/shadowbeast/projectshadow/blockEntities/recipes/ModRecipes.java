package net.shadowbeast.projectshadow.blockEntities.recipes;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ProjectShadow.MOD_ID);
    public static final RegistryObject<RecipeSerializer<WinterFurnaceRecipe>> WINTER_FURNACE_SERIALIZER =
            SERIALIZERS.register("winter_furnace", () -> WinterFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AlloyFurnaceRecipe>> ALLOYING_SERIALIZER =
            SERIALIZERS.register("alloying", () -> AlloyFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CrusherRecipe>> CRUSHING_SERIALIZER =
            SERIALIZERS.register("crushing", () -> CrusherRecipe.Serializer.INSTANCE);

    
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}

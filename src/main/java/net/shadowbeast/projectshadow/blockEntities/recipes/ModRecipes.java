package net.shadowbeast.projectshadow.blockEntities.recipes;

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


    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ProjectShadow.MOD_ID);
  public static final RegistryObject<RecipeType<WinterFurnaceRecipe>> WINTER_FURNACE_TYPE =
            SERIALIZERS.register("winter_furnace", () -> WinterFurnaceRecipe.Type.INSTANCE);
    public static final RegistryObject<RecipeType<AlloyFurnaceRecipe>> ALLOYING_TYPE =
            SERIALIZERS.register("alloying", () -> AlloyFurnaceRecipe.Type.INSTANCE);
    public static final RegistryObject<RecipeType<CrusherRecipe>> CRUSHING_TYPE =
            SERIALIZERS.register("crushing", () -> CrusherRecipe.Type.INSTANCE);
    
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}

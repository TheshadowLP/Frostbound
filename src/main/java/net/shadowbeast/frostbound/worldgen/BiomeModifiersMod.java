package net.shadowbeast.frostbound.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.frostbound.Frostbound;
import org.jetbrains.annotations.NotNull;

public class BiomeModifiersMod {
    public static final ResourceKey<BiomeModifier> ADD_FROZEN_TREE = registerKey("frozen_tree");
    public static final ResourceKey<BiomeModifier> ADD_BIG_FROZEN_TREE = registerKey("big_frozen_tree");
    public static final ResourceKey<BiomeModifier> ADD_AQUANIUM_ORE = registerKey("aquanium_ore");
    public static final ResourceKey<BiomeModifier> ADD_BONE_ORE = registerKey("bone_ore");
    public static final ResourceKey<BiomeModifier> ADD_ENDERIUM_END_ORE = registerKey("enderium_end_ore");
    public static final ResourceKey<BiomeModifier> ADD_FROZEN_GEM_ORE = registerKey("frozen_gem_ore");
    public static final ResourceKey<BiomeModifier> ADD_LUMINITE_ORE = registerKey("luminite_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_FIRERITE_ORE = registerKey("nether_firerite_ore");
    public static final ResourceKey<BiomeModifier> ADD_PLATINUM_ORE = registerKey("platinum_ore");
    public static final ResourceKey<BiomeModifier> ADD_SAND_AQUANIUM_ORE = registerKey("sand_aquanium_ore");
    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE = registerKey("silver_ore");
    public static final ResourceKey<BiomeModifier> ADD_TITANIUM_ORE = registerKey("titanium_ore");
    public static final ResourceKey<BiomeModifier> ADD_BAUXITE = registerKey("bauxite");
    public static final ResourceKey<BiomeModifier> ADD_FROZEN_STONE = registerKey("frozen_stone");

    public static void bootstrap(@NotNull BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_AQUANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.AQUANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_BAUXITE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.BAUXITE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_BONE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.BONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_ENDERIUM_END_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.ENDERIUM_END_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_FROZEN_GEM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_TAIGA),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.FROZEN_GEM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_LUMINITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.LUMINITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_FIRERITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.NETHER_FIRERITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PLATINUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.PLATINUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SAND_AQUANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.SAND_AQUANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SILVER_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.SILVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TITANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.TITANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_FROZEN_STONE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_SNOWY),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.FROZEN_STONE_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));


        context.register(ADD_FROZEN_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_SNOWY), HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.FROZEN_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_BIG_FROZEN_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_SNOWY), HolderSet.direct(placedFeatures.getOrThrow(PlacedFeaturesMod.BIG_FROZEN_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

    }
    private static @NotNull ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Frostbound.MOD_ID, name));
    }
}

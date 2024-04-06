package net.shadowbeast.projectshadow.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.worldgen.ore.ModOrePlacement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FROZEN_PLACED_KEY = registerKey("frozen_tree");
    public static final ResourceKey<PlacedFeature> BIG_FROZEN_PLACED_KEY = registerKey("big_frozen_tree");
    public static final ResourceKey<PlacedFeature> AQUANIUM_ORE_PLACED_KEY = registerKey("aquanium_ore");
    public static final ResourceKey<PlacedFeature> BONE_ORE_PLACED_KEY = registerKey("bone_ore");
    public static final ResourceKey<PlacedFeature> ENDERIUM_END_ORE_PLACED_KEY = registerKey("enderium_end_ore");
    public static final ResourceKey<PlacedFeature> FROZEN_GEM_ORE_PLACED_KEY = registerKey("frozen_gem_ore");
    public static final ResourceKey<PlacedFeature> LUMINITE_ORE_PLACED_KEY = registerKey("luminite_ore");
    public static final ResourceKey<PlacedFeature> NETHER_FIRERITE_ORE_PLACED_KEY = registerKey("nether_firerite_ore");
    public static final ResourceKey<PlacedFeature> PLATINUM_ORE_PLACED_KEY = registerKey("platinum_ore");
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final ResourceKey<PlacedFeature> SAND_AQUANIUM_ORE_PLACED_KEY = registerKey("sand_aquanium_ore");
    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_KEY = registerKey("silver_ore");
    public static final ResourceKey<PlacedFeature> SULFUR_ORE_PLACED_KEY = registerKey("sulfur_ore");
    public static final ResourceKey<PlacedFeature> TITANIUM_ORE_PLACED_KEY = registerKey("titanium_ore");
    public static final ResourceKey<PlacedFeature> BAUXITE_PLACED_KEY = registerKey("bauxite");

    public static void bootstrap(@NotNull BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, AQUANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AQUANIUM_ORE_KEY), ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(85))));
        register(context, BONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BONE_ORE_KEY), ModOrePlacement.commonOrePlacement(15, HeightRangePlacement.uniform(VerticalAnchor.absolute(40), VerticalAnchor.absolute(150))));
        register(context, ENDERIUM_END_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ENDERIUM_END_ORE_ORE_KEY), ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(35), VerticalAnchor.absolute(65))));
        register(context, FROZEN_GEM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_GEM_ORE_KEY), ModOrePlacement.commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(60))));
        register(context, LUMINITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LUMINITE_ORE_KEY), ModOrePlacement.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_FIRERITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_FIRERITE_ORE_KEY), ModOrePlacement.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(60), VerticalAnchor.absolute(100))));
        register(context, PLATINUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLATINUM_ORE_KEY), ModOrePlacement.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.absolute(40), VerticalAnchor.absolute(65))));
        register(context, RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RUBY_ORE_KEY), ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(-45), VerticalAnchor.absolute(40))));
        register(context, SAND_AQUANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAND_AQUANIUM_ORE_KEY), ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(85))));
        register(context, SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY), ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(65), VerticalAnchor.absolute(90))));
        register(context, SULFUR_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SULFUR_ORE_KEY), ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.absolute(55), VerticalAnchor.absolute(60))));
        register(context, TITANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TITANIUM_ORE_KEY), ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(55), VerticalAnchor.absolute(70))));
        register(context, BAUXITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BAUXITE_KEY), ModOrePlacement.rareOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(50))));

        register(context, FROZEN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_KEY), VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), ModBlocks.FROZEN_SAPLING.get()));
        register(context, BIG_FROZEN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BIG_FROZEN_KEY), VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), ModBlocks.FROZEN_SAPLING.get()));
    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ProjectShadow.MOD_ID, name));
    }
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}

package net.shadowbeast.projectshadow.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FROZEN_PLACED_KEY = registerKey();
    public static void bootstrap(@NotNull BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.FROZEN_SAPLING.get()));

    }private static @NotNull ResourceKey<PlacedFeature> registerKey() {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ProjectShadow.MOD_ID, "frozen_placed"));
    }
    private static void register(@NotNull BootstapContext<PlacedFeature> context, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(ModPlacedFeatures.FROZEN_PLACED_KEY, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}

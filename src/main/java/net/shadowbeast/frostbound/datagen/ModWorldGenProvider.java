package net.shadowbeast.frostbound.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.worldgen.BiomeModifiersMod;
import net.shadowbeast.frostbound.worldgen.ConfiguredFeaturesMod;
import net.shadowbeast.frostbound.worldgen.PlacedFeaturesMod;
import net.shadowbeast.frostbound.worldgen.world.biome.ModBiomes;
import net.shadowbeast.frostbound.worldgen.dimension.FrostspireDimension;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeaturesMod::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeaturesMod::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.LEVEL_STEM, FrostspireDimension::bootstrapStem)
            .add(Registries.DIMENSION_TYPE, FrostspireDimension::bootstrapType)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifiersMod::bootstrap);
    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Frostbound.MOD_ID));
    }
}

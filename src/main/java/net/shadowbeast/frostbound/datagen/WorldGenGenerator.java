package net.shadowbeast.frostbound.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.world.biome.ModBiomes;
import net.shadowbeast.frostbound.worldgen.BiomeModifiersMod;
import net.shadowbeast.frostbound.worldgen.ConfiguredFeaturesMod;
import net.shadowbeast.frostbound.worldgen.PlacedFeaturesMod;
import net.shadowbeast.frostbound.worldgen.dimension.FrostboundDimension;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
public class WorldGenGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
    public WorldGenGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Frostbound.MOD_ID));
    }
}
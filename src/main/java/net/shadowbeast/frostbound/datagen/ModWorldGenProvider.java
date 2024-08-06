package net.shadowbeast.frostbound.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.world.biome.ModBiomes;
import net.shadowbeast.frostbound.worldgen.dimension.FrostboundDimension;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.LEVEL_STEM, FrostboundDimension::bootstrapStem)
            .add(Registries.DIMENSION_TYPE, FrostboundDimension::bootstrapType);
    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Frostbound.MOD_ID));
    }
}

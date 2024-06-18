package net.shadowbeast.arcanemysteries.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();

    public WorldGenGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ArcaneMysteries.MOD_ID));
    }
}

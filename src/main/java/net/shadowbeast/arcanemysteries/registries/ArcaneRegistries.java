package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.core.data.*;

public class ArcaneRegistries 
{
    // World Registries
    public static final ResourceKey<Registry<BlockTempData>> BLOCK_TEMP_DATA = ResourceKey.createRegistryKey(new ResourceLocation(ArcaneMysteries.MOD_ID, "block/block_temp"));
    public static final ResourceKey<Registry<BiomeTempData>> BIOME_TEMP_DATA = ResourceKey.createRegistryKey(new ResourceLocation(ArcaneMysteries.MOD_ID, "world/biome_temp"));
    public static final ResourceKey<Registry<DimensionTempData>> DIMENSION_TEMP_DATA = ResourceKey.createRegistryKey(new ResourceLocation(ArcaneMysteries.MOD_ID, "world/dimension_temp"));
    public static final ResourceKey<Registry<StructureTempData>> STRUCTURE_TEMP_DATA = ResourceKey.createRegistryKey(new ResourceLocation(ArcaneMysteries.MOD_ID, "world/structure_temp"));

    // Entity Registries
    public static final ResourceKey<Registry<SpawnBiomeData>> ENTITY_SPAWN_BIOME_DATA = ResourceKey.createRegistryKey(new ResourceLocation(ArcaneMysteries.MOD_ID, "entity/spawn_biome"));
}

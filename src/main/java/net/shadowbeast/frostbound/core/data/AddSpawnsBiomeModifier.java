package net.shadowbeast.frostbound.core.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.frostbound.config.ConfigSettings;
import net.shadowbeast.frostbound.registries.BiomeCodecInit;
import net.shadowbeast.frostbound.util.helper.RegistryHelper;

import java.util.Collection;

public record AddSpawnsBiomeModifier(boolean useConfigs) implements BiomeModifier
{
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
    {
        if (phase == Phase.ADD && useConfigs)
        {
            Collection<SpawnBiomeData> spawns = ConfigSettings.ENTITY_SPAWN_BIOMES.get().get(biome.value());
            for (SpawnBiomeData spawn : spawns)
            {
                RegistryHelper.mapForgeRegistryTagList(ForgeRegistries.ENTITY_TYPES, spawn.entities())
                        .forEach(entityType ->
                        {
                            builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).removeIf(spawnerData -> spawnerData.type == entityType);
                            builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(entityType, spawn.weight(), 1, 1));
                        });
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec()
    {   return BiomeCodecInit.ADD_SPAWNS_CODEC.get();
    }
}

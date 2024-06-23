package net.shadowbeast.arcanemysteries.config;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.codec.requierment.NbtRequirement;
import net.shadowbeast.arcanemysteries.core.data.*;
import net.shadowbeast.arcanemysteries.registries.ArcaneRegistries;
import net.shadowbeast.arcanemysteries.registries.BlockTempRegistry;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.temprature.temp.BlockTemp;
import net.shadowbeast.arcanemysteries.util.MathHelper;
import net.shadowbeast.arcanemysteries.util.helper.RegistryHelper;
import oshi.util.tuples.Triplet;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigRegistryHandler
{
    public static void collectConfigRegistries(RegistryAccess registries)
    {
        if (registries == null)
        {   ArcaneMysteries.LOGGER.error("Failed to load registries from null RegistryAccess");
            return;
        }

        /*
         Read mod-related tags for config settings
         */
        /*
         Fetch JSON registries
        */

        Set<Holder<BlockTempData>> blockTemps = registries.registryOrThrow(ArcaneRegistries.BLOCK_TEMP_DATA).holders().collect(Collectors.toSet());
        Set<Holder<BiomeTempData>> biomeTemps = registries.registryOrThrow(ArcaneRegistries.BIOME_TEMP_DATA).holders().collect(Collectors.toSet());
        Set<Holder<DimensionTempData>> dimensionTemps = registries.registryOrThrow(ArcaneRegistries.DIMENSION_TEMP_DATA).holders().collect(Collectors.toSet());
        Set<Holder<StructureTempData>> structureTemps = registries.registryOrThrow(ArcaneRegistries.STRUCTURE_TEMP_DATA).holders().collect(Collectors.toSet());

        Set<Holder<SpawnBiomeData>> spawnBiomes = registries.registryOrThrow(ArcaneRegistries.ENTITY_SPAWN_BIOME_DATA).holders().collect(Collectors.toSet());


        // block temperatures
        addBlockTempConfigs(blockTemps);
        logRegistryLoaded(String.format("Loaded %s block temperatures", blockTemps.size()), blockTemps);
        // biome temperatures
        addBiomeTempConfigs(biomeTemps, registries);
        logRegistryLoaded(String.format("Loaded %s biome temperatures", biomeTemps.size()), biomeTemps);
        // dimension temperatures
        addDimensionTempConfigs(dimensionTemps, registries);
        logRegistryLoaded(String.format("Loaded %s dimension temperatures", dimensionTemps.size()), dimensionTemps);
        // structure temperatures
        addStructureTempConfigs(structureTemps, registries);
        logRegistryLoaded(String.format("Loaded %s structure temperatures", structureTemps.size()), structureTemps);

        // spawn biomes
        addSpawnBiomeConfigs(spawnBiomes, registries);
        logRegistryLoaded(String.format("Loaded %s entity spawn biomes", spawnBiomes.size()), spawnBiomes);
    }

    private static void logRegistryLoaded(String message, Set<?> registry)
    {
        if (registry.isEmpty())
        {   message += ".";
        }
        else message += ":";
        ArcaneMysteries.LOGGER.info(message, registry.size());
        if (registry.isEmpty())
        {   return;
        }
        for (Object entry : registry)
        {
            if (entry instanceof Holder<?> holder)
            {   ArcaneMysteries.LOGGER.info("{}", holder.get());
            }
            else
            {   ArcaneMysteries.LOGGER.info("{}", entry);
            }
        }
    }

    private static void addBlockTempConfigs(Set<Holder<BlockTempData>> blockTemps)
    {
        blockTemps.forEach(holder ->
        {
            BlockTempData blockTempData = holder.get();
            Block[] blocks = RegistryHelper.mapForgeRegistryTagList(ForgeRegistries.BLOCKS, blockTempData.blocks()).toArray(Block[]::new);
            BlockTemp blockTemp = new BlockTemp(blocks)
            {
                final double temperature = blockTempData.temperature();
                final double maxEffect = blockTempData.maxEffect();
                final boolean fade = blockTempData.fade();
                final BlockPredicate condition = blockTempData.condition();
                final CompoundTag tag = blockTempData.nbt().orElse(null);
                final double range = blockTempData.range();

                @Override
                public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
                {
                    if (level instanceof ServerLevel serverLevel)
                    {
                        if (!condition.test(serverLevel, pos))
                        {   return 0;
                        }
                    }
                    if (tag != null)
                    {
                        BlockEntity blockEntity = level.getBlockEntity(pos);
                        if (blockEntity != null)
                        {
                            CompoundTag blockTag = blockEntity.saveWithFullMetadata();
                            for (String key : tag.getAllKeys())
                            {
                                if (!NbtRequirement.compareNbt(tag.get(key), blockTag.get(key), true))
                                {   return 0;
                                }
                            }
                        }
                    }
                    return fade
                            ? MathHelper.blend(temperature, 0, distance, 0, Math.min(range, ConfigSettings.BLOCK_RANGE.get()))
                            : temperature;
                }

                @Override
                public double maxEffect()
                {   return temperature > 0 ? maxEffect : super.maxEffect();
                }

                @Override
                public double minEffect()
                {   return temperature < 0 ? -maxEffect : super.minEffect();
                }
            };

            BlockTempRegistry.register(blockTemp);
        });
    }

    private static void addBiomeTempConfigs(Set<Holder<BiomeTempData>> biomeTemps, RegistryAccess registryAccess)
    {
        biomeTemps.forEach(holder ->
        {
            BiomeTempData biomeTempData = holder.get();
            for (Biome biome : RegistryHelper.mapVanillaRegistryTagList(Registries.BIOME, biomeTempData.biomes(), registryAccess))
            {
                Temperature.Units units = biomeTempData.units();
                if (biomeTempData.isOffset())
                {   ConfigSettings.BIOME_OFFSETS.get().put(biome, new Triplet<>(Temperature.convert(biomeTempData.min(), units, Temperature.Units.MC, true),
                        Temperature.convert(biomeTempData.max(), units, Temperature.Units.MC, true),
                        biomeTempData.units()));
                }
                else
                {   ConfigSettings.BIOME_TEMPS.get().put(biome, new Triplet<>(Temperature.convert(biomeTempData.min(), units, Temperature.Units.MC, true),
                        Temperature.convert(biomeTempData.max(), units, Temperature.Units.MC, true),
                        biomeTempData.units()));
                }
            }
        });
    }

    private static void addDimensionTempConfigs(Set<Holder<DimensionTempData>> dimensionTemps, RegistryAccess registryAccess)
    {
        dimensionTemps.forEach(holder ->
        {
            DimensionTempData dimensionTempData = holder.get();
            for (DimensionType dimension : RegistryHelper.mapVanillaRegistryTagList(Registries.DIMENSION_TYPE, dimensionTempData.dimensions(), registryAccess))
            {
                Temperature.Units units = dimensionTempData.units();
                if (dimensionTempData.isOffset())
                {   ConfigSettings.DIMENSION_OFFSETS.get().put(dimension, Pair.of(Temperature.convert(dimensionTempData.temperature(), units, Temperature.Units.MC, true),
                        dimensionTempData.units()));
                }
                else
                {   ConfigSettings.DIMENSION_TEMPS.get().put(dimension, Pair.of(Temperature.convert(dimensionTempData.temperature(), units, Temperature.Units.MC, true),
                        dimensionTempData.units()));
                }
            }
        });
    }

    private static void addStructureTempConfigs(Set<Holder<StructureTempData>> structureTemps, RegistryAccess registryAccess)
    {
        structureTemps.forEach(holder ->
        {
            StructureTempData structureTempData = holder.get();
            for (Structure structure : RegistryHelper.mapVanillaRegistryTagList(Registries.STRUCTURE, structureTempData.structures(), registryAccess))
            {
                double temperature = Temperature.convert(structureTempData.temperature(), structureTempData.units(), Temperature.Units.MC, !structureTempData.isOffset());
                if (structureTempData.isOffset())
                {   ConfigSettings.STRUCTURE_OFFSETS.get().put(structure.type(), Pair.of(temperature, structureTempData.units()));
                }
                else
                {   ConfigSettings.STRUCTURE_TEMPS.get().put(structure.type(), Pair.of(temperature, structureTempData.units()));
                }
            }
        });
    }

    private static void addSpawnBiomeConfigs(Set<Holder<SpawnBiomeData>> spawnBiomes, RegistryAccess registryAccess)
    {
        spawnBiomes.forEach(holder ->
        {
            SpawnBiomeData spawnBiomeData = holder.get();
            for (Biome biome : RegistryHelper.mapVanillaRegistryTagList(Registries.BIOME, spawnBiomeData.biomes(), registryAccess))
            {   ConfigSettings.ENTITY_SPAWN_BIOMES.get().put(biome, spawnBiomeData);
            }
        });
    }

    private static <T> Set<Holder<T>> parseConfigData(ResourceKey<Registry<T>> registry, Codec<T> codec)
    {
        Set<Holder<T>> output = new HashSet<>();

        Path coldSweatDataPath = FMLPaths.CONFIGDIR.get().resolve("arcanemysteries/data").resolve(registry.location().getPath());
        File jsonDirectory = coldSweatDataPath.toFile();

        if (!jsonDirectory.exists())
        {   return output;
        }
        else for (File file : findFilesRecursive(jsonDirectory))
        {
            if (file.getName().endsWith(".json"))
            {
                try (FileReader reader = new FileReader(file))
                {
                    codec.parse(JsonOps.INSTANCE, GsonHelper.parse(reader))
                            .resultOrPartial(ArcaneMysteries.LOGGER::error)
                            .ifPresent(insulator -> output.add(Holder.direct(insulator)));
                }
                catch (Exception e)
                {   ArcaneMysteries.LOGGER.error(String.format("Failed to parse JSON config setting in %s: %s", registry.location(), file.getName()), e);
                }
            }
        }
        return output;
    }

    private static List<File> findFilesRecursive(File directory)
    {
        List<File> files = new ArrayList<>();
        File[] filesInDirectory = directory.listFiles();
        if (filesInDirectory == null)
        {   return files;
        }
        for (File file : filesInDirectory)
        {
            if (file.isDirectory())
            {   files.addAll(findFilesRecursive(file));
            }
            else
            {   files.add(file);
            }
        }
        return files;
    }
}

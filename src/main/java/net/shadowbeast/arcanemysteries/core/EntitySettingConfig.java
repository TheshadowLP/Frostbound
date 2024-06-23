package net.shadowbeast.arcanemysteries.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.shadowbeast.arcanemysteries.util.nbt.ListBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class EntitySettingConfig
{
    public static final ForgeConfigSpec SPEC;
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> insulatedEntities;
    private static final ForgeConfigSpec.ConfigValue<List<?>> goatFurGrowth;
    private static final ForgeConfigSpec.ConfigValue<List<?>> chameleonShedTimings;
    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> chameleonBiomes;
    private static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> goatBiomes;
    private static final EntitySettingConfig INSTANCE = new EntitySettingConfig();

    static
    {
        /*
         Insulated Entities
         */
        BUILDER.push("Entity Settings");
        insulatedEntities = BUILDER
                .comment("List of entities that will insulate the player when riding them",
                        "A value of 0 provides no insulation; 1 provides full insulation",
                        "Format: [[\"entity_id\", coldResistance, hotResistance], [\"entity_id\", coldResistance, hotResistance], etc...]")
                .defineListAllowEmpty(List.of("Insulated Mounts"), () -> Arrays.asList(),
                        it ->
                        {
                            if (it instanceof List<?>)
                            {   List<?> list = ((List<?>) it);
                                return list.size() == 3 && list.get(0) instanceof String && list.get(1) instanceof Number && list.get(2) instanceof Number;
                            }
                            return false;
                        });

        goatFurGrowth = BUILDER
                .comment("Defines how often a goat will try to grow its fur, the growth cooldown after shearing, and the chance of it succeeding",
                        "Format: [ticks, cooldown, chance]")
                .defineList("Goat Fur Growth Timings", List.of(
                                1200, 2400, 0.20
                        ),
                        it -> it instanceof Number);

        chameleonShedTimings = BUILDER
                .comment("Defines how often a chameleon will try to shed its skin, the cooldown after shedding, and the chance of it succeeding",
                        "Format: [ticks, cooldown, chance]")
                .defineList("Chameleon Shedding Timings", List.of(
                                100, 36000, 0.10
                        ),
                        it -> it instanceof Number);

        BUILDER.pop();

        BUILDER.push("Mob Spawning");
        chameleonBiomes = BUILDER
                .comment("Defines the biomes that Chameleons can spawn in",
                        "Format: [[\"biome_id\", weight], [\"biome_id\", weight], etc...]")
                .defineList("Chameleon Spawn Biomes", ListBuilder.begin(
                                        List.of("minecraft:bamboo_jungle", 80),
                                        List.of("minecraft:jungle", 80),
                                        List.of("minecraft:sparse_jungle", 35),
                                        List.of("minecraft:desert", 1)
                                ).build(),
                        it -> it instanceof List<?> list && list.size() == 2 && list.get(0) instanceof String && list.get(1) instanceof Number);

        goatBiomes = BUILDER
                .comment("Defines additional biomes that goats can spawn in",
                        "Format: [[\"biome_id\", weight], [\"biome_id\", weight], etc...]",
                        "Not affected by the \"Increase Goat Spawns\" option")
                .defineList("Goat Spawn Biomes", ListBuilder.begin(
                                        List.of("minecraft:frozen_peaks", 8),
                                        List.of("minecraft:jagged_peaks", 8),
                                        List.of("minecraft:snowy_slopes", 8),
                                        List.of("minecraft:meadow", 3),
                                        List.of("minecraft:windswept_hills", 6),
                                        List.of("minecraft:windswept_forest", 6),
                                        List.of("minecraft:windswept_gravelly_hills", 4),
                                        List.of("minecraft:grove", 5),
                                        List.of("minecraft:stony_peaks", 8)
                                ).build(),
                        it -> it instanceof List<?> list && list.size() == 2 && list.get(0) instanceof String && list.get(1) instanceof Number);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void setup()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path csConfigPath = Paths.get(configPath.toAbsolutePath().toString(), "arcanemysteries");

        // Create the config folder
        try
        {   Files.createDirectory(csConfigPath);
        }
        catch (Exception ignored) {}

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "arcanemysteries/entity_settings.toml");
    }

    public static EntitySettingConfig getInstance()
    {   return INSTANCE;
    }

    /* Getters */

    public List<? extends List<?>> getInsulatedEntities()
    {   return insulatedEntities.get();
    }

    public List<?> getGoatFurStats()
    {   return goatFurGrowth.get();
    }

    public List<?> getChameleonShedStats()
    {   return chameleonShedTimings.get();
    }

    public List<? extends List<?>> getChameleonSpawnBiomes()
    {   return chameleonBiomes.get();
    }

    public List<? extends List<?>> getGoatSpawnBiomes()
    {   return goatBiomes.get();
    }

    /* Setters */

    public void setGoatFurStats(List<? extends Number> list)
    {   goatFurGrowth.set(list);
    }

    public void setChameleonShedStats(List<? extends Number> list)
    {   chameleonShedTimings.set(list);
    }
}

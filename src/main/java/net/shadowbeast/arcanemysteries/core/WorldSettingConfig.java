package net.shadowbeast.arcanemysteries.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.util.nbt.ListBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorldSettingConfig {
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> biomeOffsets;
    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> biomeTemps;
    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> dimensionOffsets;
    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> dimensionTemps;
    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> structureOffsets;
    public static final ForgeConfigSpec.ConfigValue<List<? extends List<?>>> structureTemps;

    public static final ForgeConfigSpec.ConfigValue<Double> caveInsulation;

    public static final ForgeConfigSpec.ConfigValue<List<? extends List<Object>>> blockTemps;
    public static final ForgeConfigSpec.IntValue blockRange;

    public static final ForgeConfigSpec.ConfigValue<Boolean> coldSoulFire;

    public static ForgeConfigSpec.ConfigValue<List<? extends Number>> summerTemps;
    public static ForgeConfigSpec.ConfigValue<List<? extends Number>> autumnTemps;
    public static ForgeConfigSpec.ConfigValue<List<? extends Number>> winterTemps;
    public static ForgeConfigSpec.ConfigValue<List<? extends Number>> springTemps;

    public static final ForgeConfigSpec.ConfigValue<Double> hearthEffect;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> hearthSpreadWhitelist;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> hearthSpreadBlacklist;

    public static final ForgeConfigSpec.ConfigValue<Boolean> checkSleep;

    public static final WorldSettingConfig INSTANCE = new WorldSettingConfig();

    static
    {
        /*
         Dimensions
         */
        BUILDER.comment("Format: [[\"dimension_1\", temperature1, *units], [\"dimension_2\", temperature2, *units]... etc]",
                        "Common dimension IDs: minecraft:overworld, minecraft:the_nether, minecraft:the_end")
                .push("Dimensions");

        dimensionOffsets = BUILDER
                .comment("Applies an offset to the world's temperature across an entire dimension")
                .defineList("Dimension Temperature Offsets", List.of(
                        List.of("minecraft:the_nether", 0.7),
                        List.of("minecraft:the_end", -0.1)
                ), it -> it instanceof List<?> list
                        && list.get(0) instanceof String
                        && list.get(1) instanceof Number
                        && (list.size() < 3 || list.get(2) instanceof String));

        dimensionTemps = BUILDER
                .comment("Overrides existing dimension temperatures & offsets",
                        "Also overrides temperatures of all biomes in the dimension")
                .defineList("Dimension Temperatures", List.of(
                        // No default values
                ), it -> it instanceof List<?> list
                        && list.get(0) instanceof String
                        && list.get(1) instanceof Number
                        && (list.size() < 3 || list.get(2) instanceof String));

        BUILDER.pop();

        /*
         Biomes
         */
        BUILDER.comment("Format: [[\"biome_1\", tempLow, tempHigh, *units], [\"biome_2\", tempLow, tempHigh, *units]... etc]",
                        "temp-low: The temperature of the biome at midnight",
                        "temp-high: The temperature of the biome at noon",
                        "units: Optional. The units of the temperature (\"C\" or \"F\". Defaults to MC units)")
                .push("Biomes");

        biomeOffsets = BUILDER
                .comment("Applies an offset to the temperature of a biome")
                .defineList("Biome Temperature Offsets", List.of(),
                        it ->
                        {
                            if (it instanceof List<?> list)
                            {
                                if (list.size() == 2)
                                {   ArcaneMysteries.LOGGER.warn("Falling back to legacy code for config setting {} in \"Biome Temperature Offsets\". Please update to the new standard!", list.get(0));
                                }
                                return list.get(0) instanceof String && list.get(1) instanceof Number && (list.size() < 3 || list.get(2) instanceof Number) && (list.size() < 4 || list.get(3) instanceof String);
                            }
                            return false;
                        });


        biomeTemps = BUILDER
                .comment("Defines the temperature of a biome, overriding existing biome temperatures & offsets.")
                .defineList("Biome Temperatures", net.shadowbeast.arcanemysteries.util.nbt.ListBuilder.begin(
                                        List.of("minecraft:soul_sand_valley", 53, 53, "F"),
                                        List.of("minecraft:old_growth_birch_forest", 58, 72, "F"),
                                        List.of("minecraft:river", 60, 70, "F"),
                                        List.of("minecraft:swamp", 72, 84, "F"),
                                        List.of("minecraft:savanna", 70, 95, "F"),
                                        List.of("minecraft:savanna_plateau", 76, 98, "F"),
                                        List.of("minecraft:windswept_savanna", 67, 90, "F"),
                                        List.of("minecraft:taiga", 44, 62, "F"),
                                        List.of("minecraft:snowy_taiga", 19, 48, "F"),
                                        List.of("minecraft:old_growth_pine_taiga", 48, 62, "F"),
                                        List.of("minecraft:old_growth_spruce_taiga", 48, 62, "F"),
                                        List.of("minecraft:desert", 48, 115, "F"),
                                        List.of("minecraft:stony_shore", 50, 64, "F"),
                                        List.of("minecraft:snowy_beach", 38, 52, "F"),
                                        List.of("minecraft:snowy_slopes", 24, 38, "F"),
                                        List.of("minecraft:windswept_forest", 48, 66, "F"),
                                        List.of("minecraft:frozen_peaks", 15, 33, "F"),
                                        List.of("minecraft:warm_ocean", 67, 76, "F"),
                                        List.of("minecraft:deep_frozen_ocean", 56, 65, "F"),
                                        List.of("minecraft:jungle", 76, 87, "F"),
                                        List.of("minecraft:bamboo_jungle", 76, 87, "F"),
                                        List.of("minecraft:badlands", 84, 120, "F"),
                                        List.of("minecraft:wooded_badlands", 80, 108, "F"),
                                        List.of("minecraft:eroded_badlands", 88, 120, "F"),
                                        List.of("minecraft:deep_dark", 63, 63, "F")
                                ).build(),
                        it ->
                        {
                            if (it instanceof List<?> list)
                            {
                                if (list.size() == 2)
                                {   ArcaneMysteries.LOGGER.warn("Falling back to legacy code for config setting {} in \"Biome Temperatures\". Please update to the new standard!", list.get(0));
                                }
                                return list.get(0) instanceof String && list.get(1) instanceof Number && (list.size() < 3 || list.get(2) instanceof Number) && (list.size() < 4 || list.get(3) instanceof String);
                            }
                            return false;
                        });

        BUILDER.pop();


        BUILDER.push("Blocks");

        blockTemps = BUILDER
                .comment("Allows for adding simple BlockTemps without the use of Java mods",
                        "Format (All temperatures are in Minecraft units):",
                        "[[\"block-ids\", <temperature>, <range (max 7)>, <*true/false: falloff>, <*max effect>, <*predicates>], [etc...], [etc...]]",
                        "(* = optional) (1 \u00B0MC = 42 \u00B0F/ 23.33 \u00B0C)",
                        "",
                        "Arguments:",
                        "block-ids: multiple IDs can be used by separating them with commas (i.e: \"minecraft:torch,minecraft:wall_torch\")",
                        "temperature: the temperature of the block, in Minecraft units",
                        "falloff: the block is less effective as distance increases",
                        "max effect: the maximum temperature change this block can cause to a player (even with multiple blocks)",
                        "predicates: the state that the block has to be in for the temperature to be applied (lit=true for a campfire, for example).",
                        "Multiple predicates can be used by separating them with commas (i.e: \"lit=true,waterlogged=false\")")
                .defineList("Block Temperatures", Arrays.asList
                                (
                                        Arrays.asList("minecraft:soul_fire",     -0.476, 7, true, 0.8),
                                        Arrays.asList("minecraft:fire",           0.476, 7, true, 0.8),
                                        Arrays.asList("minecraft:magma_block",      0.5, 3, true, 0.6),
                                        Arrays.asList("minecraft:soul_campfire", -0.476, 7, true, 0.6, "lit=true"),
                                        Arrays.asList("minecraft:ice",            -0.15, 4, true, 0.5),
                                        Arrays.asList("minecraft:packed_ice",     -0.25, 4, true, 1.0),
                                        Arrays.asList("minecraft:blue_ice",       -0.35, 4, true, 1.0),
                                        Arrays.asList("minecraft:lava_cauldron",    0.5, 7, true, 1.5)
                                ),
                        it -> it instanceof List<?> list && list.size() >= 3 && list.get(0) instanceof String && list.get(1) instanceof Number && list.get(2) instanceof Number);

        blockRange = BUILDER
                .comment("The maximum range of blocks' area of effect",
                        "Note: This will not change anything unless blocks are configured to utilize the expanded range",
                        "This value is capped at 16 for performance reasons")
                .defineInRange("Block Range", 7, 1, 16);

        BUILDER.pop();


        BUILDER.push("Misc");

        caveInsulation = BUILDER
                .comment("The amount of temperature normalization from being deep underground",
                        "0.0 = no insulation, 1.0 = full insulation")
                .defineInRange("Cave Insulation Strength", 1.0, 0.0, 1.0);

        structureTemps = BUILDER
                .comment("Overrides the world temperature when the player is within this structure",
                        "Format: [[\"structure_1\", temperature1, *units], [\"structure_2\", temperature2, *units]... etc]",
                        "(* = optional)")
                .defineList("Structure Temperatures", List.of(
                        List.of("minecraft:igloo", 65, "F")
                ), it -> it instanceof List<?> list
                        && list.get(0) instanceof String
                        && list.get(1) instanceof Number
                        && (list.size() < 3 || list.get(2) instanceof String));

        structureOffsets = BUILDER
                .comment("Offsets the world temperature when the player is within this structure",
                        "Format: [[\"structure_1\", offset1, *units], [\"structure_2\", offset2, *units]... etc]",
                        "(* = optional)")
                .defineList("Structure Temperature Offsets", List.of(
                        // empty
                ), it -> it instanceof List<?> list
                        && list.get(0) instanceof String
                        && list.get(1) instanceof Number
                        && (list.size() < 3 || list.get(2) instanceof String));

        checkSleep = BUILDER
                .comment("When set to true, players cannot sleep if they are cold or hot enough to die")
                .define("Check Sleeping Conditions", true);

        coldSoulFire = BUILDER
                .comment("Converts damage dealt by Soul Fire to cold damage (default: true)",
                        "Does not affect the block's temperature")
                .define("Cold Soul Fire", true);

        BUILDER.pop();


        BUILDER.push("Hearth");

        hearthEffect = BUILDER
                .comment("How effective the hearth is at normalizing temperature")
                .defineInRange("Hearth Strength", 0.75, 0, 1.0);

        hearthSpreadWhitelist = BUILDER
                .comment("List of additional blocks that the hearth can spread through",
                        "Use this list if the hearth isn't spreading through particular blocks that it should")
                .defineListAllowEmpty(List.of("Hearth Spread Whitelist"), ListBuilder.begin(
                                        "minecraft:iron_bars",
                                        "#minecraft:leaves")

                                .build(),
                        o -> o instanceof String);

        hearthSpreadBlacklist = BUILDER
                .comment("List of additional blocks that the hearth cannot spread through",
                        "Use this list if the hearth is spreading through particular blocks that it shouldn't")
                .defineList("Hearth Spread Blacklist", List.of(
                        ),
                        o -> o instanceof String);

        BUILDER.pop();


        /* Serene Seasons config */

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

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "arcanemysteries/world_settings.toml");
    }

    public static WorldSettingConfig getInstance()
    {   return INSTANCE;
    }

    /* Getters */

    public List<? extends List<?>> getBiomeTempOffsets()
    {   return biomeOffsets.get();
    }
    public List<? extends List<?>> getBiomeTemperatures()
    {   return biomeTemps.get();
    }

    public List<? extends List<?>> getDimensionTempOffsets()
    {   return dimensionOffsets.get();
    }
    public List<? extends List<?>> getDimensionTemperatures()
    {   return dimensionTemps.get();
    }

    public List<? extends List<?>> getStructureTempOffsets()
    {   return structureOffsets.get();
    }
    public List<? extends List<?>> getStructureTemperatures()
    {   return structureTemps.get();
    }

    public List<? extends List<Object>> getBlockTemps()
    {   return blockTemps.get();
    }
    public int getBlockRange()
    {   return blockRange.get();
    }

    public double getCaveInsulation()
    {   return caveInsulation.get();
    }

    public double getHearthEffect()
    {   return hearthEffect.get();
    }
    public List<String> getHearthSpreadWhitelist()
    {   return (List<String>) hearthSpreadWhitelist.get();
    }
    public List<String> getHearthSpreadBlacklist()
    {   return (List<String>) hearthSpreadBlacklist.get();
    }

    public Double[] getSummerTemps()
    {   return summerTemps.get().stream().map(Number::doubleValue).toArray(Double[]::new);
    }
    public Double[] getAutumnTemps()
    {   return autumnTemps.get().stream().map(Number::doubleValue).toArray(Double[]::new);
    }
    public Double[] getWinterTemps()
    {   return winterTemps.get().stream().map(Number::doubleValue).toArray(Double[]::new);
    }
    public Double[] getSpringTemps()
    {   return springTemps.get().stream().map(Number::doubleValue).toArray(Double[]::new);
    }

    public boolean isSoulFireCold()
    {   return coldSoulFire.get();
    }

    public boolean isSleepChecked()
    {   return checkSleep.get();
    }

    /* Setters */

    public synchronized void setBiomeTemperatures(List<? extends List<?>> temps)
    {   synchronized (biomeTemps)
    {   biomeTemps.set(temps);
    }
    }

    public synchronized void setBiomeTempOffsets(List<? extends List<?>> offsets)
    {   synchronized (biomeOffsets)
    {   biomeOffsets.set(offsets);
    }
    }

    public synchronized void setDimensionTemperatures(List<? extends List<?>> temps)
    {   synchronized (dimensionTemps)
    {   dimensionTemps.set(temps);
    }
    }

    public synchronized void setDimensionTempOffsets(List<? extends List<?>> offsets)
    {   synchronized (dimensionOffsets)
    {   dimensionOffsets.set(offsets);
    }
    }

    public synchronized void setStructureTemperatures(List<? extends List<?>> temps)
    {   synchronized (structureTemps)
    {   structureTemps.set(temps);
    }
    }

    public synchronized void setStructureTempOffsets(List<? extends List<?>> offsets)
    {   synchronized (structureOffsets)
    {   structureOffsets.set(offsets);
    }
    }

    public synchronized void setBlockTemps(List<? extends List<Object>> temps)
    {   synchronized (blockTemps)
    {   blockTemps.set(temps);
    }
    }

    public synchronized void setBlockRange(int range)
    {   synchronized (blockRange)
    {   blockRange.set(range);
    }
    }

    public synchronized void setCaveInsulation(double insulation)
    {   synchronized (caveInsulation)
    {   caveInsulation.set(insulation);
    }
    }

    public synchronized void setHearthSpreadWhitelist(List<ResourceLocation> whitelist)
    {   synchronized (hearthSpreadWhitelist)
    {   hearthSpreadWhitelist.set(whitelist.stream().map(ResourceLocation::toString).collect(Collectors.toList()));
    }
    }

    public synchronized void setHearthSpreadBlacklist(List<ResourceLocation> blacklist)
    {   synchronized (hearthSpreadBlacklist)
    {   hearthSpreadBlacklist.set(blacklist.stream().map(ResourceLocation::toString).collect(Collectors.toList()));
    }
    }
}

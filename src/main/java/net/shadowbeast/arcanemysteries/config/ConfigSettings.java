package net.shadowbeast.arcanemysteries.config;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.core.*;
import net.shadowbeast.arcanemysteries.core.data.SpawnBiomeData;
import net.shadowbeast.arcanemysteries.temprature.Temperature;
import net.shadowbeast.arcanemysteries.util.DynamicHolder;
import net.shadowbeast.arcanemysteries.util.helper.RegistryHelper;
import org.joml.Vector2i;
import oshi.util.tuples.Triplet;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ConfigSettings {
        public static final Map<String, DynamicHolder<?>> CONFIG_SETTINGS = new ConcurrentHashMap<>();

        public static Difficulty DEFAULT_DIFFICULTY = Difficulty.NORMAL;

        // Settings visible in the config screen
        public static final DynamicHolder<Integer> DIFFICULTY;
        public static final DynamicHolder<Double> MAX_TEMP;
        public static final DynamicHolder<Double> MIN_TEMP;
        public static final DynamicHolder<Double> TEMP_RATE;
        public static final DynamicHolder<Double> TEMP_DAMAGE;
        public static final DynamicHolder<Boolean> FIRE_RESISTANCE_ENABLED;
        public static final DynamicHolder<Boolean> ICE_RESISTANCE_ENABLED;
        public static final DynamicHolder<Boolean> DAMAGE_SCALING;
        public static final DynamicHolder<Boolean> REQUIRE_THERMOMETER;
        public static final DynamicHolder<Integer> GRACE_LENGTH;
        public static final DynamicHolder<Boolean> GRACE_ENABLED;

        // Other Difficulty Settings
        public static final DynamicHolder<Double> HEARTS_FREEZING_PERCENTAGE;
        public static final DynamicHolder<Double> COLD_MINING_IMPAIRMENT;
        public static final DynamicHolder<Double> COLD_MOVEMENT_SLOWDOWN;
        public static final DynamicHolder<Double> COLD_KNOCKBACK_REDUCTION;
        public static final DynamicHolder<Double> HEATSTROKE_FOG_DISTANCE;

        // World Settings
        public static final DynamicHolder<Map<Biome, Triplet<Double, Double, Temperature.Units>>> BIOME_TEMPS;
        public static final DynamicHolder<Map<Biome, Triplet<Double, Double, Temperature.Units>>> BIOME_OFFSETS;
        public static final DynamicHolder<Map<DimensionType, Pair<Double, Temperature.Units>>> DIMENSION_TEMPS;
        public static final DynamicHolder<Map<DimensionType, Pair<Double, Temperature.Units>>> DIMENSION_OFFSETS;
        public static final DynamicHolder<Map<StructureType<?>, Pair<Double, Temperature.Units>>> STRUCTURE_TEMPS;
        public static final DynamicHolder<Map<StructureType<?>, Pair<Double, Temperature.Units>>> STRUCTURE_OFFSETS;
        public static final DynamicHolder<Double> CAVE_INSULATION;
        public static final DynamicHolder<Double[]> SUMMER_TEMPS;
        public static final DynamicHolder<Double[]> AUTUMN_TEMPS;
        public static final DynamicHolder<Double[]> WINTER_TEMPS;
        public static final DynamicHolder<Double[]> SPRING_TEMPS;

        // Block settings
        public static final DynamicHolder<Integer> BLOCK_RANGE;
        public static final DynamicHolder<Boolean> COLD_SOUL_FIRE;
        public static final DynamicHolder<List<Block>> HEARTH_SPREAD_WHITELIST;
        public static final DynamicHolder<List<Block>> HEARTH_SPREAD_BLACKLIST;
        public static final DynamicHolder<Double> HEARTH_STRENGTH;

        // Item settings
        public static final DynamicHolder<Boolean> CHECK_SLEEP_CONDITIONS;


        public static final DynamicHolder<Integer> WATERSKIN_STRENGTH;

        // Entity Settings
        public static final DynamicHolder<Triplet<Integer, Integer, Double>> SHED_TIMINGS;
        public static final DynamicHolder<Multimap<Biome, SpawnBiomeData>> ENTITY_SPAWN_BIOMES;

        // Client Settings
        /* NULL ON THE SERVER */
        public static final DynamicHolder<Boolean> CELSIUS;
        public static final DynamicHolder<Integer> TEMP_OFFSET;
        public static final DynamicHolder<Double> TEMP_SMOOTHING;

        public static final DynamicHolder<Vector2i> BODY_ICON_POS;
        public static final DynamicHolder<Boolean> BODY_ICON_ENABLED;
        public static final DynamicHolder<Boolean> MOVE_BODY_ICON_WHEN_ADVANCED;

        public static final DynamicHolder<Vector2i> BODY_READOUT_POS;
        public static final DynamicHolder<Boolean> BODY_READOUT_ENABLED;

        public static final DynamicHolder<Vector2i> WORLD_GAUGE_POS;
        public static final DynamicHolder<Boolean> WORLD_GAUGE_ENABLED;

        public static final DynamicHolder<Boolean> CUSTOM_HOTBAR_LAYOUT;
        public static final DynamicHolder<Boolean> ICON_BOBBING;

        public static final DynamicHolder<Boolean> HEARTH_DEBUG;

        public static final DynamicHolder<Boolean> SHOW_CONFIG_BUTTON;
        public static final DynamicHolder<Vector2i> CONFIG_BUTTON_POS;

        public static final DynamicHolder<Boolean> DISTORTION_EFFECTS;

        public static final DynamicHolder<Boolean> HIGH_CONTRAST;

        public static final DynamicHolder<Boolean> SHOW_CREATIVE_WARNING;


        // Makes the settings instantiation collapsible & easier to read
        static {
            DIFFICULTY = addSyncedSetting("difficulty", () -> MainSettingConfig.getInstance().getDifficulty(),
                    encoder -> ConfigHelper.serializeNbtInt(encoder, "Difficulty"),
                    decoder -> decoder.getInt("Difficulty"),
                    saver -> MainSettingConfig.getInstance().setDifficulty(saver));

            MAX_TEMP = addSyncedSetting("max_temp", () -> MainSettingConfig.getInstance().getMaxTempHabitable(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "MaxTemp"),
                    decoder -> decoder.getDouble("MaxTemp"),
                    saver -> MainSettingConfig.getInstance().setMaxHabitable(saver));

            MIN_TEMP = addSyncedSetting("min_temp", () -> MainSettingConfig.getInstance().getMinTempHabitable(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "MinTemp"),
                    decoder -> decoder.getDouble("MinTemp"),
                    saver -> MainSettingConfig.getInstance().setMinHabitable(saver));

            TEMP_RATE = addSyncedSetting("temp_rate", () -> MainSettingConfig.getInstance().getRateMultiplier(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "TempRate"),
                    decoder -> decoder.getDouble("TempRate"),
                    saver -> MainSettingConfig.getInstance().setRateMultiplier(saver));

            TEMP_DAMAGE = addSyncedSetting("temp_damage", () -> MainSettingConfig.getInstance().getTempDamage(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "TempDamage"),
                    decoder -> decoder.getDouble("TempDamage"),
                    saver -> MainSettingConfig.getInstance().setTempDamage(saver));

            FIRE_RESISTANCE_ENABLED = addSyncedSetting("fire_resistance_enabled", () -> MainSettingConfig.getInstance().isFireResistanceEnabled(),
                    encoder -> ConfigHelper.serializeNbtBool(encoder, "FireResistanceEnabled"),
                    decoder -> decoder.getBoolean("FireResistanceEnabled"),
                    saver -> MainSettingConfig.getInstance().setFireResistanceEnabled(saver));

            ICE_RESISTANCE_ENABLED = addSyncedSetting("ice_resistance_enabled", () -> MainSettingConfig.getInstance().isIceResistanceEnabled(),
                    encoder -> ConfigHelper.serializeNbtBool(encoder, "IceResistanceEnabled"),
                    decoder -> decoder.getBoolean("IceResistanceEnabled"),
                    saver -> MainSettingConfig.getInstance().setIceResistanceEnabled(saver));

            DAMAGE_SCALING = addSyncedSetting("damage_scaling", () -> MainSettingConfig.getInstance().doDamageScaling(),
                    encoder -> ConfigHelper.serializeNbtBool(encoder, "DamageScaling"),
                    decoder -> decoder.getBoolean("DamageScaling"),
                    saver -> MainSettingConfig.getInstance().setDamageScaling(saver));

            REQUIRE_THERMOMETER = addSyncedSetting("require_thermometer", () -> MainSettingConfig.getInstance().thermometerRequired(),
                    encoder -> ConfigHelper.serializeNbtBool(encoder, "RequireThermometer"),
                    decoder -> decoder.getBoolean("RequireThermometer"),
                    saver -> MainSettingConfig.getInstance().setRequireThermometer(saver));

            GRACE_LENGTH = addSyncedSetting("grace_length", () -> MainSettingConfig.getInstance().getGracePeriodLength(),
                    encoder -> ConfigHelper.serializeNbtInt(encoder, "GraceLength"),
                    decoder -> decoder.getInt("GraceLength"),
                    saver -> MainSettingConfig.getInstance().setGracePeriodLength(saver));

            GRACE_ENABLED = addSyncedSetting("grace_enabled", () -> MainSettingConfig.getInstance().isGracePeriodEnabled(),
                    encoder -> ConfigHelper.serializeNbtBool(encoder, "GraceEnabled"),
                    decoder -> decoder.getBoolean("GraceEnabled"),
                    saver -> MainSettingConfig.getInstance().setGracePeriodEnabled(saver));


            HEARTS_FREEZING_PERCENTAGE = addSyncedSetting("hearts_freezing_percentage", () -> MainSettingConfig.getInstance().getHeartsFreezingPercentage(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "HeartsFreezingPercentage"),
                    decoder -> decoder.getDouble("HeartsFreezingPercentage"),
                    saver -> MainSettingConfig.getInstance().setHeartsFreezingPercentage(saver));

            COLD_MINING_IMPAIRMENT = addSyncedSetting("cold_mining_slowdown", () -> MainSettingConfig.getInstance().getColdMiningImpairment(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "ColdMiningImpairment"),
                    decoder -> decoder.getDouble("ColdMiningImpairment"),
                    saver -> MainSettingConfig.getInstance().setColdMiningImpairment(saver));

            COLD_MOVEMENT_SLOWDOWN = addSyncedSetting("cold_movement_slowdown", () -> MainSettingConfig.getInstance().getColdMovementSlowdown(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "ColdMovementSlowdown"),
                    decoder -> decoder.getDouble("ColdMovementSlowdown"),
                    saver -> MainSettingConfig.getInstance().setColdMovementSlowdown(saver));

            COLD_KNOCKBACK_REDUCTION = addSyncedSetting("cold_knockback_reduction", () -> MainSettingConfig.getInstance().getColdKnockbackReduction(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "ColdKnockbackReduction"),
                    decoder -> decoder.getDouble("ColdKnockbackReduction"),
                    saver -> MainSettingConfig.getInstance().setColdKnockbackReduction(saver));

            HEATSTROKE_FOG_DISTANCE = addSyncedSetting("heatstroke_fog_distance", () -> MainSettingConfig.getInstance().getHeatstrokeFogDistance(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "HeatstrokeFogDistance"),
                    decoder -> decoder.getDouble("HeatstrokeFogDistance"),
                    saver -> MainSettingConfig.getInstance().setHeatstrokeFogDistance(saver));


            BIOME_TEMPS = addSyncedSetting("biome_temps", () -> ConfigHelper.getBiomesWithValues(WorldSettingConfig.getInstance().getBiomeTemperatures(), true),
                    encoder -> ConfigHelper.serializeBiomeTemps(encoder, "BiomeTemps"),
                    decoder -> ConfigHelper.deserializeBiomeTemps(decoder, "BiomeTemps"),
                    saver -> WorldSettingConfig.getInstance().setBiomeTemperatures(saver.entrySet().stream()
                            .map(entry ->
                            {
                                ResourceLocation biome = RegistryHelper.getBiomeId(entry.getKey());
                                if (biome == null) return null;

                                Temperature.Units units = entry.getValue().getC();
                                double min = Temperature.convert(entry.getValue().getA(), Temperature.Units.MC, units, true);
                                double max = Temperature.convert(entry.getValue().getB(), Temperature.Units.MC, units, true);

                                return Arrays.asList(biome.toString(), min, max, units.toString());
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())));

            BIOME_OFFSETS = addSyncedSetting("biome_offsets", () -> ConfigHelper.getBiomesWithValues(WorldSettingConfig.getInstance().getBiomeTempOffsets(), false),
                    encoder -> ConfigHelper.serializeBiomeTemps(encoder, "BiomeOffsets"),
                    decoder -> ConfigHelper.deserializeBiomeTemps(decoder, "BiomeOffsets"),
                    saver -> WorldSettingConfig.getInstance().setBiomeTempOffsets(saver.entrySet().stream()
                            .map(entry ->
                            {
                                ResourceLocation biome = RegistryHelper.getBiomeId(entry.getKey());
                                if (biome == null) return null;

                                Temperature.Units units = entry.getValue().getC();
                                double min = Temperature.convert(entry.getValue().getA(), Temperature.Units.MC, units, false);
                                double max = Temperature.convert(entry.getValue().getB(), Temperature.Units.MC, units, false);

                                return Arrays.asList(biome.toString(), min, max, units.toString());
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())));

            DIMENSION_TEMPS = addSyncedSetting("dimension_temps", () -> ConfigHelper.getDimensionsWithValues(WorldSettingConfig.getInstance().getDimensionTemperatures(), true),
                    encoder -> ConfigHelper.serializeDimensionTemps(encoder, "DimensionTemps"),
                    decoder -> ConfigHelper.deserializeDimensionTemps(decoder, "DimensionTemps"),
                    saver -> WorldSettingConfig.getInstance().setDimensionTemperatures(saver.entrySet().stream()
                            .map(entry ->
                            {
                                ResourceLocation dim = RegistryHelper.getDimensionId(entry.getKey());
                                if (dim == null) return null;

                                Temperature.Units units = entry.getValue().getSecond();
                                double temp = Temperature.convert(entry.getValue().getFirst(), Temperature.Units.MC, units, true);

                                return Arrays.asList(dim.toString(), temp, units.toString());
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())));

            DIMENSION_OFFSETS = addSyncedSetting("dimension_offsets", () -> ConfigHelper.getDimensionsWithValues(WorldSettingConfig.getInstance().getDimensionTempOffsets(), false),
                    encoder -> ConfigHelper.serializeDimensionTemps(encoder, "DimensionOffsets"),
                    decoder -> ConfigHelper.deserializeDimensionTemps(decoder, "DimensionOffsets"),
                    saver -> WorldSettingConfig.getInstance().setDimensionTempOffsets(saver.entrySet().stream()
                            .map(entry ->
                            {
                                ResourceLocation dim = RegistryHelper.getDimensionId(entry.getKey());
                                if (dim == null) return null;

                                Temperature.Units units = entry.getValue().getSecond();
                                double temp = Temperature.convert(entry.getValue().getFirst(), Temperature.Units.MC, units, false);

                                return Arrays.asList(dim.toString(), temp, units.toString());
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())));

            STRUCTURE_TEMPS = addSyncedSetting("structure_temperatures", () -> ConfigHelper.getStructuresWithValues(WorldSettingConfig.getInstance().getStructureTemperatures(), true),
                    encoder -> ConfigHelper.serializeStructureTemps(encoder, "StructureTemperatures"),
                    decoder -> ConfigHelper.deserializeStructureTemps(decoder, "StructureTemperatures"),
                    saver -> WorldSettingConfig.getInstance().setStructureTemperatures(saver.entrySet().stream()
                            .map(entry ->
                            {
                                ResourceLocation struct = RegistryHelper.getStructureId(entry.getKey());
                                if (struct == null) return null;

                                Temperature.Units units = entry.getValue().getSecond();
                                double temp = Temperature.convert(entry.getValue().getFirst(), Temperature.Units.MC, units, true);

                                return Arrays.asList(struct.toString(), temp, units.toString());
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())));

            STRUCTURE_OFFSETS = addSyncedSetting("structure_offsets", () -> ConfigHelper.getStructuresWithValues(WorldSettingConfig.getInstance().getStructureTempOffsets(), false),
                    encoder -> ConfigHelper.serializeStructureTemps(encoder, "StructureOffsets"),
                    decoder -> ConfigHelper.deserializeStructureTemps(decoder, "StructureOffsets"),
                    saver -> WorldSettingConfig.getInstance().setStructureTempOffsets(saver.entrySet().stream()
                            .map(entry ->
                            {
                                ResourceLocation struct = RegistryHelper.getStructureId(entry.getKey());
                                if (struct == null) return null;

                                Temperature.Units units = entry.getValue().getSecond();
                                double temp = Temperature.convert(entry.getValue().getFirst(), Temperature.Units.MC, units, false);

                                return Arrays.asList(struct.toString(), temp, units.toString());
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())));

            CAVE_INSULATION = addSyncedSetting("cave_insulation", () -> WorldSettingConfig.getInstance().getCaveInsulation(),
                    encoder -> ConfigHelper.serializeNbtDouble(encoder, "CaveInsulation"),
                    decoder -> decoder.getDouble("CaveInsulation"),
                    saver -> WorldSettingConfig.getInstance().setCaveInsulation(saver));

            CHECK_SLEEP_CONDITIONS = addSetting("check_sleep_conditions", () -> WorldSettingConfig.getInstance().isSleepChecked());



            WATERSKIN_STRENGTH = addSetting("waterskin_strength", () -> ItemSettingConfig.getInstance().getWaterskinStrength());
            
            

            SHED_TIMINGS = addSyncedSetting("shed_timings", () ->
                    {
                        List<?> entry = EntitySettingConfig.getInstance().getChameleonShedStats();
                        return new Triplet<>(((Number) entry.get(0)).intValue(), ((Number) entry.get(1)).intValue(), ((Number) entry.get(2)).doubleValue());
                    },
                    encoder ->
                    {
                        CompoundTag tag = new CompoundTag();
                        tag.put("Interval", IntTag.valueOf(encoder.getA()));
                        tag.put("Cooldown", IntTag.valueOf(encoder.getB()));
                        tag.put("Chance", DoubleTag.valueOf(encoder.getC()));
                        return tag;
                    },
                    decoder ->
                    {
                        int interval = decoder.getInt("Interval");
                        int cooldown = decoder.getInt("Cooldown");
                        double chance = decoder.getDouble("Chance");
                        return new Triplet<>(interval, cooldown, chance);
                    },
                    saver ->
                    {
                        List<Number> list = new ArrayList<>();
                        list.add(saver.getA());
                        list.add(saver.getB());
                        list.add(saver.getC());
                        EntitySettingConfig.getInstance().setChameleonShedStats(list);
                    });

            ENTITY_SPAWN_BIOMES = addSetting("entity_spawn_biomes", () ->
            {
                Multimap<Biome, SpawnBiomeData> map = HashMultimap.create();
                // Function to read biomes from configs and put them in the config settings
                Consumer<List<? extends List<?>>> configReader = configBiomes ->
                {
                    for (List<?> entry : configBiomes) {
                        String biomeId = ((String) entry.get(0));
                        List<Biome> biomes = ConfigHelper.parseRegistryItems(Registries.BIOME, biomeId);
                        Either<TagKey<Biome>, Biome> biomeEither;
                        if (biomeId.charAt(0) == '#') {
                            biomeEither = Either.left(TagKey.create(Registries.BIOME, new ResourceLocation(biomeId.substring(1))));
                        } else {
                            Biome biome = RegistryHelper.getBiome(new ResourceLocation(biomeId));
                            if (biome != null)
                                biomeEither = Either.right(biome);
                            else
                                continue;
                        }
                    }
                };

                // Parse goat and chameleon biomes
                configReader.accept(EntitySettingConfig.getInstance().getChameleonSpawnBiomes());
                configReader.accept(EntitySettingConfig.getInstance().getGoatSpawnBiomes());

                return map;
            });
            
            BLOCK_RANGE = addSyncedSetting("block_range", () -> WorldSettingConfig.getInstance().getBlockRange(),
                    encoder -> ConfigHelper.serializeNbtInt(encoder, "BlockRange"),
                    decoder -> decoder.getInt("BlockRange"),
                    saver -> WorldSettingConfig.getInstance().setBlockRange(saver));

            COLD_SOUL_FIRE = addSetting("cold_soul_fire", () -> WorldSettingConfig.getInstance().isSoulFireCold());

            HEARTH_SPREAD_WHITELIST = addSyncedSetting("hearth_spread_whitelist", () -> ConfigHelper.getBlocks(WorldSettingConfig.getInstance().getHearthSpreadWhitelist().toArray(new String[0])),
                    encoder ->
                    {
                        CompoundTag tag = new CompoundTag();
                        ListTag list = new ListTag();
                        for (Block entry : encoder) {
                            list.add(StringTag.valueOf(ForgeRegistries.BLOCKS.getKey(entry).toString()));
                        }
                        tag.put("HearthWhitelist", list);
                        return tag;
                    },
                    decoder ->
                    {
                        List<Block> list = new ArrayList<>();
                        for (Tag entry : decoder.getList("HearthWhitelist", 8)) {
                            list.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entry.getAsString())));
                        }
                        return list;
                    },
                    saver -> WorldSettingConfig.getInstance().setHearthSpreadWhitelist(saver.stream().map(ForgeRegistries.BLOCKS::getKey).toList()));

            HEARTH_SPREAD_BLACKLIST = addSyncedSetting("hearth_spread_blacklist", () -> ConfigHelper.getBlocks(WorldSettingConfig.getInstance().getHearthSpreadBlacklist().toArray(new String[0])),
                    encoder ->
                    {
                        CompoundTag tag = new CompoundTag();
                        ListTag list = new ListTag();
                        for (Block entry : encoder) {
                            list.add(StringTag.valueOf(ForgeRegistries.BLOCKS.getKey(entry).toString()));
                        }
                        tag.put("HearthBlacklist", list);
                        return tag;
                    },
                    decoder ->
                    {
                        List<Block> list = new ArrayList<>();
                        for (Tag entry : decoder.getList("HearthBlacklist", 8)) {
                            list.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entry.getAsString())));
                        }
                        return list;
                    },
                    saver -> WorldSettingConfig.getInstance().setHearthSpreadBlacklist(saver.stream().map(ForgeRegistries.BLOCKS::getKey).toList()));

            HEARTH_STRENGTH = addSetting("hearth_effect", () -> WorldSettingConfig.getInstance().getHearthEffect());

            CELSIUS = addClientSetting("celsius", () -> ClientSettingConfig.getInstance().isCelsius());

            TEMP_OFFSET = addClientSetting("temp_offset", () -> ClientSettingConfig.getInstance().getTempOffset());

            TEMP_SMOOTHING = addClientSetting("temp_smoothing", () -> ClientSettingConfig.getInstance().getTempSmoothing());

            BODY_ICON_POS = addClientSetting("body_icon_pos", () -> new Vector2i(ClientSettingConfig.getInstance().getBodyIconX(),
                    ClientSettingConfig.getInstance().getBodyIconY()));
            BODY_ICON_ENABLED = addClientSetting("body_icon_enabled", () -> ClientSettingConfig.getInstance().isBodyIconEnabled());

            MOVE_BODY_ICON_WHEN_ADVANCED = addClientSetting("move_body_icon_for_advanced", () -> ClientSettingConfig.getInstance().moveBodyIconWhenAdvanced());

            BODY_READOUT_POS = addClientSetting("body_readout_pos", () -> new Vector2i(ClientSettingConfig.getInstance().getBodyReadoutX(),
                    ClientSettingConfig.getInstance().getBodyReadoutY()));
            BODY_READOUT_ENABLED = addClientSetting("body_readout_enabled", () -> ClientSettingConfig.getInstance().isBodyReadoutEnabled());

            WORLD_GAUGE_POS = addClientSetting("world_gauge_pos", () -> new Vector2i(ClientSettingConfig.getInstance().getWorldGaugeX(),
                    ClientSettingConfig.getInstance().getWorldGaugeY()));
            WORLD_GAUGE_ENABLED = addClientSetting("world_gauge_enabled", () -> ClientSettingConfig.getInstance().isWorldGaugeEnabled());

            CUSTOM_HOTBAR_LAYOUT = addClientSetting("custom_hotbar_layout", () -> ClientSettingConfig.getInstance().isCustomHotbarLayout());
            ICON_BOBBING = addClientSetting("icon_bobbing", () -> ClientSettingConfig.getInstance().isIconBobbing());

            HEARTH_DEBUG = addClientSetting("hearth_debug", () -> ClientSettingConfig.getInstance().isHearthDebug());

            SHOW_CONFIG_BUTTON = addClientSetting("show_config_button", () -> ClientSettingConfig.getInstance().showConfigButton());
            CONFIG_BUTTON_POS = addClientSetting("config_button_pos", () -> new Vector2i(ClientSettingConfig.getInstance().getConfigButtonPos().get(0),
                    ClientSettingConfig.getInstance().getConfigButtonPos().get(1)));

            DISTORTION_EFFECTS = addClientSetting("distortion_effects", () -> ClientSettingConfig.getInstance().areDistortionsEnabled());

            HIGH_CONTRAST = addClientSetting("high_contrast", () -> ClientSettingConfig.getInstance().isHighContrast());

            SHOW_CREATIVE_WARNING = addClientSetting("show_creative_warning", () -> ClientSettingConfig.getInstance().showCreativeWarning());


            SUMMER_TEMPS = addSetting("summer_temps", () -> new Double[3]);
            AUTUMN_TEMPS = addSetting("autumn_temps", () -> new Double[3]);
            WINTER_TEMPS = addSetting("winter_temps",  () -> new Double[3]);
            SPRING_TEMPS = addSetting("spring_temps", () -> new Double[3]);
        }

        public enum Difficulty {
            SUPER_EASY(Map.of(
                    "min_temp", () -> Temperature.convert(40, Temperature.Units.F, Temperature.Units.MC, true),
                    "max_temp", () -> Temperature.convert(120, Temperature.Units.F, Temperature.Units.MC, true),
                    "temp_rate", () -> 0.5,
                    "require_thermometer", () -> false,
                    "fire_resistance_enabled", () -> true,
                    "ice_resistance_enabled", () -> true,
                    "damage_scaling", () -> false
            )),

            EASY(Map.of(
                    "min_temp", () -> Temperature.convert(45, Temperature.Units.F, Temperature.Units.MC, true),
                    "max_temp", () -> Temperature.convert(110, Temperature.Units.F, Temperature.Units.MC, true),
                    "temp_rate", () -> 0.75,
                    "require_thermometer", () -> false,
                    "fire_resistance_enabled", () -> true,
                    "ice_resistance_enabled", () -> true,
                    "damage_scaling", () -> false
            )),

            NORMAL(Map.of(
                    "min_temp", () -> Temperature.convert(50, Temperature.Units.F, Temperature.Units.MC, true),
                    "max_temp", () -> Temperature.convert(100, Temperature.Units.F, Temperature.Units.MC, true),
                    "temp_rate", () -> 1.0,
                    "require_thermometer", () -> true,
                    "fire_resistance_enabled", () -> true,
                    "ice_resistance_enabled", () -> true,
                    "damage_scaling", () -> true
            )),

            HARD(Map.of(
                    "min_temp", () -> Temperature.convert(60, Temperature.Units.F, Temperature.Units.MC, true),
                    "max_temp", () -> Temperature.convert(90, Temperature.Units.F, Temperature.Units.MC, true),
                    "temp_rate", () -> 1.5,
                    "require_thermometer", () -> true,
                    "fire_resistance_enabled", () -> false,
                    "ice_resistance_enabled", () -> false,
                    "damage_scaling", () -> true
            )),

            CUSTOM(Map.of());

            private final Map<String, Supplier<?>> settings;

            Difficulty(Map<String, Supplier<?>> settings) {
                this.settings = settings;
            }

            public <T> T getSetting(String id) {
                return (T) settings.get(id).get();
            }

            public <T> T getOrDefault(String id, T defaultValue) {
                return (T) settings.getOrDefault(id, () -> defaultValue).get();
            }

            public void load() {
                settings.forEach((id, loader) -> CONFIG_SETTINGS.get(id).set(loader.get()));
            }
        }

        public static <T> DynamicHolder<T> addSyncedSetting(String id, Supplier<T> supplier, Function<T, CompoundTag> writer, Function<CompoundTag, T> reader, Consumer<T> saver) {
            DynamicHolder<T> loader = DynamicHolder.createSynced(supplier, writer, reader, saver);
            CONFIG_SETTINGS.put(id, loader);
            return loader;
        }

        public static <T> DynamicHolder<T> addSetting(String id, Supplier<T> supplier) {
            DynamicHolder<T> loader = DynamicHolder.create(supplier);
            CONFIG_SETTINGS.put(id, loader);
            return loader;
        }

        public static <T> DynamicHolder<T> addClientSetting(String id, Supplier<T> supplier) {
            return FMLEnvironment.dist == Dist.CLIENT
                    ? addSetting(id, supplier)
                    : new DynamicHolder<>(() -> null);
        }

        public static Map<String, CompoundTag> encode() {
            Map<String, CompoundTag> map = new HashMap<>();
            CONFIG_SETTINGS.forEach((key, value) ->
            {
                if (value.isSynced()) {
                    map.put(key, value.encode());
                }
            });
            return map;
        }

        public static void decode(String key, CompoundTag tag) {
            CONFIG_SETTINGS.computeIfPresent(key, (k, value) ->
            {
                value.decode(tag);
                return value;
            });
        }

        public static void saveValues() {
            CONFIG_SETTINGS.values().forEach(value ->
            {
                if (value.isSynced()) {
                    value.save();
                }
            });
        }

        public static void load(RegistryAccess registryAccess) {
            CONFIG_SETTINGS.values().forEach(DynamicHolder::load);
            if (registryAccess != null) {
                ConfigRegistryHandler.collectConfigRegistries(registryAccess);
            } else {
                ArcaneMysteries.LOGGER.warn("Loading Arcane Mysteries config settings without loading registries. This is normal during startup.");
            }
        }
    }

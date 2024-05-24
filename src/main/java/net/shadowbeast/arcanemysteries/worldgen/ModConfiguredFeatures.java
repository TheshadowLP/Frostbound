package net.shadowbeast.arcanemysteries.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.blocks.ModBlocks;

import java.util.List;


public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_KEY = registerKey("frozen_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_FROZEN_KEY = registerKey("big_frozen_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUANIUM_ORE_KEY = registerKey("aquanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_ORE_KEY = registerKey("bone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDERIUM_END_ORE_ORE_KEY = registerKey("enderium_end_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_GEM_ORE_KEY = registerKey("frozen_gem_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LUMINITE_ORE_KEY = registerKey("luminite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_FIRERITE_ORE_KEY = registerKey("nether_firerite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_KEY = registerKey("platinum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAND_AQUANIUM_ORE_KEY = registerKey("sand_aquanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = registerKey("silver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_ORE_KEY = registerKey("sulfur_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_KEY = registerKey("titanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAUXITE_KEY = registerKey("bauxite");
    public static final ResourceKey<ConfiguredFeature<?, ?>>FROZEN_STONE_KEY = registerKey("frozen_stone_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceabeles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceabeles = new BlockMatchTest(Blocks.END_STONE);
        RuleTest sandReplaceabeles = new BlockMatchTest(Blocks.SAND);

        List<OreConfiguration.TargetBlockState> AquaniumOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.AQUANIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> BoneOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.BONE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> LuminiteOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.LUMINITE_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_LUMINITE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> EnderiumEndOre = List.of(OreConfiguration.target(endReplaceabeles, ModBlocks.ENDERIUM_END_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> FrozenGemOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.FROZEN_GEM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> NetherFireriteOre = List.of(OreConfiguration.target(netherrackReplaceabeles, ModBlocks.NETHER_FIRERITE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> PlatinumOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.PLATINUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> RubyOre = List.of(OreConfiguration.target(deepslateReplaceabeles, ModBlocks.RUBY_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> SandAquaniumOre = List.of(OreConfiguration.target(sandReplaceabeles, ModBlocks.SAND_AQUANIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> SilverOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.SILVER_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> SulfurOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.SULFUR_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> TitaniumOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.TITANIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> FrozenStone = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.FROZEN_STONE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceabeles, ModBlocks.FROZEN_STONE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> Bauxite = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.BAUXITE.get().defaultBlockState()));

        register(context, AQUANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(AquaniumOre, 8));
        register(context, BAUXITE_KEY, Feature.ORE, new OreConfiguration(Bauxite, 40));
        register(context, BONE_ORE_KEY, Feature.ORE, new OreConfiguration(BoneOre, 9));
        register(context, LUMINITE_ORE_KEY, Feature.ORE, new OreConfiguration(LuminiteOre, 6));
        register(context, ENDERIUM_END_ORE_ORE_KEY, Feature.ORE, new OreConfiguration(EnderiumEndOre, 3));
        register(context, FROZEN_GEM_ORE_KEY, Feature.ORE, new OreConfiguration(FrozenGemOre, 5));
        register(context, NETHER_FIRERITE_ORE_KEY, Feature.ORE, new OreConfiguration(NetherFireriteOre, 5));
        register(context, PLATINUM_ORE_KEY, Feature.ORE, new OreConfiguration(PlatinumOre, 10));
        register(context, RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(RubyOre, 4));
        register(context, SAND_AQUANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(SandAquaniumOre, 6));
        register(context, SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(SilverOre, 11));
        register(context, SULFUR_ORE_KEY, Feature.ORE, new OreConfiguration(SulfurOre, 7));
        register(context, TITANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(TitaniumOre, 6));
        register(context, FROZEN_STONE_KEY, Feature.ORE, new OreConfiguration(FrozenStone, 64));

        register(context, FROZEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.FROZEN_LOG.get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(ModBlocks.FROZEN_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(2,3), UniformInt.of(0,2), UniformInt.of(1,2)), new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());
        register(context, BIG_FROZEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.FROZEN_LOG.get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(ModBlocks.FROZEN_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(2,3), UniformInt.of(0,2), UniformInt.of(1,2)), new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());

    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ArcaneMysteries.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
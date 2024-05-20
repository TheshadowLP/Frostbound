package net.shadowbeast.projectshadow.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.crops.KohlrabiCropBlock;
import net.shadowbeast.projectshadow.blocks.crops.PepperCropBlock;
import net.shadowbeast.projectshadow.blocks.custom.*;
import net.shadowbeast.projectshadow.block_entities.blocks.furnace.AlloyFurnaceBlock;
import net.shadowbeast.projectshadow.block_entities.blocks.crusher.CrusherBlock;
import net.shadowbeast.projectshadow.block_entities.blocks.furnace.WinterFurnaceBlock;
import net.shadowbeast.projectshadow.fluid.ModFluids;
import net.shadowbeast.projectshadow.items.ModItems;
import net.shadowbeast.projectshadow.util.ModWoodTypes;
import net.shadowbeast.projectshadow.worldgen.tree.FrozenTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectShadow.MOD_ID);

    // Block Entities
    public static final RegistryObject<Block> WINTER_FURNACE = registerBlock("winter_furnace", ()-> new WinterFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().friction(0.9f)));
    public static final RegistryObject<Block> ALLOY_FURNACE = registerBlock("alloy_furnace", () -> new AlloyFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> CRUSHER = registerBlock("crusher", () -> new CrusherBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

    //TNT
    public static final RegistryObject<Block> SULFURIC_TNT = registerBlock("sulfuric_tnt", () -> new SulfuricTNTBlock(BlockBehaviour.Properties.copy(Blocks.TNT)));

    //CROPS
    public static final RegistryObject<Block> KOHLRABI_CROP = BLOCKS.register("kohlrabi_crop", () -> new KohlrabiCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));
    public static final RegistryObject<Block> PEPPER_CROP = BLOCKS.register("pepper_crop", () -> new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    //ORES
    public static final RegistryObject<Block> AQUANIUM_ORE = registerBlock("aquanium_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BONE_ORE = registerBlock("bone_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_LUMINITE_ORE = registerBlock("deepslate_luminite_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).strength(4.5F,3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENDERIUM_END_ORE = registerBlock("enderium_end_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).strength(5.2F,4.8F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_GEM_ORE = registerBlock("frozen_gem_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(4.7F,3.8F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LUMINITE_ORE = registerBlock("luminite_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NETHER_FIRERITE_ORE = registerBlock("nether_firerite_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(5.0F,4.3F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.1F,3.1F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_EMERALD_ORE).strength(4.5F,3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> SAND_AQUANIUM_ORE = registerBlock("sand_aquanium_ore", ()-> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND).strength(1.4F,1.2F).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(2.6F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SULFUR_ORE = registerBlock("sulfur_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.2F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.2F,3.2F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> END_GOLD_ORE = registerBlock("end_gold_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.8F,4.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BAUXITE = registerBlock("bauxite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.2F,3.2F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    //ORES BLOCKS
    public static final RegistryObject<Block> AQUANIUM_BlOCK = registerBlock("aquanium_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENDERIUM_BlOCK = registerBlock("enderium_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(9.0F,12.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FIRERITE_BlOCK = registerBlock("firerite_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_GEM_BlOCK = registerBlock("frozen_gem_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LUMINITE_BlOCK = registerBlock("luminite_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATINUM_BlOCK = registerBlock("platinum_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUBY_BlOCK = registerBlock("ruby_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILVER_BlOCK = registerBlock("silver_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(3.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_BlOCK = registerBlock("steel_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SULFUR_BlOCK = registerBlock("sulfur_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,3.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_BlOCK = registerBlock("titanium_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));

    //COPPER BLOCKS
    public static final RegistryObject<Block> COPPER_BRICK = registerBlock("copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> EXPOSED_COPPER_BRICK = registerBlock("exposed_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.EXPOSED, BlockBehaviour.Properties.copy(Blocks.EXPOSED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WEATHERED_COPPER_BRICK = registerBlock("weathered_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.WEATHERED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OXIDIZED_COPPER_BRICK = registerBlock("oxidized_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.DEGRADED, BlockBehaviour.Properties.copy(Blocks.OXIDIZED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_COPPER_BRICK = registerBlock("waxed_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_COPPER_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BRICK = registerBlock("waxed_exposed_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_EXPOSED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BRICK = registerBlock("waxed_weathered_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_WEATHERED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BRICK = registerBlock("waxed_oxidized_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_OXIDIZED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CUT_COPPER_BRICK = registerBlock("cut_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.CUT_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_BRICK = registerBlock("exposed_cut_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.EXPOSED, BlockBehaviour.Properties.copy(Blocks.EXPOSED_CUT_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_BRICK = registerBlock("weathered_cut_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.WEATHERED_CUT_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_BRICK = registerBlock("oxidized_cut_copper_brick", ()-> new DegradableCopperBlock(GemDegradable.GemDegradationLevel.DEGRADED, BlockBehaviour.Properties.copy(Blocks.OXIDIZED_CUT_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_CUT_COPPER_BRICK = registerBlock("waxed_cut_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_COPPER_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_BRICK = registerBlock("waxed_exposed_cut_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_EXPOSED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_BRICK = registerBlock("waxed_weathered_cut_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_WEATHERED_COPPER).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_BRICK = registerBlock("waxed_oxidized_cut_copper_brick", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.WAXED_OXIDIZED_COPPER).requiresCorrectToolForDrops()));

    //NATURAL BLOCKS
    public static final RegistryObject<Block> FROZEN_STONE = registerBlock("frozen_stone", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.7F,6.0F)));
    public static final RegistryObject<Block> FROZEN_STONE_BRICKS = registerBlock("frozen_stone_bricks", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).strength(1.7F,6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE = registerBlock("frozen_cobblestone", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).strength(2.2F)));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE_WALL = registerBlock("frozen_cobblestone_wall", ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL)));
    public static final RegistryObject<Block> FROZEN_STONE_BRICK_WALL = registerBlock("frozen_stone_brick_wall", ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));

    //END BLOCKS
    public static final RegistryObject<EndLanternBlock> END_LANTERN = registerBlock("end_lantern", ()-> new EndLanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((p_152677_) -> {return 14;}).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> END_STONE_PILLAR = registerBlock("end_stone_pillar", ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<Block> TILED_END_STONE_BRICKS = registerBlock("tiled_end_stone_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> POLISHED_END_STONE = registerBlock("polished_end_stone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<Block> RUNIC_END_STONE_BRICKS = registerBlock("runic_end_stone_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));
    public static final RegistryObject<Block> CHISELED_PURPUR = registerBlock("chiseled_purpur", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> POLISHED_PURPUR = registerBlock("polished_purpur", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> BRICKED_PURPUR = registerBlock("bricked_purpur", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> BRICKED_PURPUR_PILLAR = registerBlock("bricked_purpur_pillar", ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> TILED_PURPUR = registerBlock("tiled_purpur", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> TILED_PURPUR_PILLAR = registerBlock("tiled_purpur_pillar", ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> RUNIC_PURPUR = registerBlock("runic_purpur", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> DRAGON_SCALE_BLOCK = registerBlock("dragon_scale_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_WART_BLOCK)));
    public static final RegistryObject<Block> END_OBSIDIAN = registerBlock("end_obsidian", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENDER_PEARL_BLOCK = registerBlock("ender_pearl_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> END_GLASS = registerBlock("end_glass", ()-> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> END_GLASS_PANE = registerBlock("end_glass_pane", ()->  new IronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

    //BARS
    public static final RegistryObject<Block> COPPER_BARS = registerBlock("copper_bars", ()-> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATINUM_BARS = registerBlock("platinum_bars", ()-> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_BARS = registerBlock("steel_bars", ()-> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_BARS = registerBlock("titanium_bars", ()-> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS).noOcclusion().requiresCorrectToolForDrops()));

    //SIGNS
    public static final RegistryObject<Block> FROZEN_SIGN = BLOCKS.register("frozen_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.FROZEN));
    public static final RegistryObject<Block> FROZEN_WALL_SIGN = BLOCKS.register("frozen_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.FROZEN));
    public static final RegistryObject<Block> FROZEN_HANGING_SIGN = BLOCKS.register("frozen_hanging_sign", () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.FROZEN));
    public static final RegistryObject<Block> FROZEN_WALL_HANGING_SIGN = BLOCKS.register("frozen_wall_hanging_sign", () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.FROZEN));

    //WOOD STUFF
    public static final RegistryObject<Block> FROZEN_LOG = registerBlock("frozen_log", ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG), true));
    public static final RegistryObject<Block> FROZEN_WOOD = registerBlock("frozen_wood", ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD), true));
    public static final RegistryObject<Block> STRIPPED_FROZEN_LOG = registerBlock("stripped_frozen_log", ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG), true));
    public static final RegistryObject<Block> STRIPPED_FROZEN_WOOD = registerBlock("stripped_frozen_wood", ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD), true));
    public static final RegistryObject<Block> FROZEN_PRESSURE_PLATE = registerBlock("frozen_pressure_plate", ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> FROZEN_DOOR = registerBlock("frozen_door", ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR),BlockSetType.OAK));
    public static final RegistryObject<Block> FROZEN_TRAPDOOR = registerBlock("frozen_trapdoor", ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR),BlockSetType.OAK));
    public static final RegistryObject<Block> FROZEN_BUTTON = registerBlock("frozen_button", ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK,30,true));
    public static final RegistryObject<Block> FROZEN_STAIRS = registerBlock("frozen_stairs", ()-> new StairBlock(() -> ModBlocks.FROZEN_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
    public static final RegistryObject<Block> FROZEN_FENCE = registerBlock("frozen_fence", ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
    public static final RegistryObject<Block> FROZEN_FENCE_GATE = registerBlock("frozen_fence_gate", ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN,SoundEvents.FENCE_GATE_CLOSE){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
    public static final RegistryObject<Block> FROZEN_SLAB = registerBlock("frozen_slab", ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
    public static final RegistryObject<Block> FROZEN_PLANKS = registerBlock("frozen_planks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
    public static final RegistryObject<Block> FROZEN_SAPLING= registerBlock("frozen_sapling", ()-> new SaplingBlock(new FrozenTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).requiresCorrectToolForDrops()));

    //LEAVES
    public static final RegistryObject<Block> FROZEN_LEAVES = registerBlock("frozen_leaves", ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}});


    //TARGET BLOCKS
    public static final RegistryObject<Block> BLUE_TARGET = registerBlock("blue_target", () -> new NewTargetBlock(BlockBehaviour.Properties.copy(Blocks.TARGET).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> YELLOW_TARGET = registerBlock("yellow_target", () -> new NewTargetBlock(BlockBehaviour.Properties.copy(Blocks.TARGET).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> GREEN_TARGET = registerBlock("green_target", () -> new NewTargetBlock(BlockBehaviour.Properties.copy(Blocks.TARGET).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> BLACK_TARGET = registerBlock("black_target", () -> new NewTargetBlock(BlockBehaviour.Properties.copy(Blocks.TARGET).sound(SoundType.GRASS)));

    //BEDROCK
    public static final RegistryObject<Block> BEDROCK = registerBlock("bedrock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(13.0F, 3600000.0F).requiresCorrectToolForDrops()));

    //FLUIDS
    public static final RegistryObject<EndLavaBlock> END_LAVA_BLOCK = BLOCKS.register("end_lava_block", () -> new EndLavaBlock(ModFluids.END_LAVA_FLUID.get(), BlockBehaviour.Properties.copy(Blocks.LAVA).noCollission().noOcclusion().noLootTable()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {RegistryObject<T> toReturn = BLOCKS.register(name, block);registerBlockItem(name, toReturn);return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}



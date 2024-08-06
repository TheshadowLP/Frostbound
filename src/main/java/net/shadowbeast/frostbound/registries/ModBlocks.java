package net.shadowbeast.frostbound.registries;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.frostbound.block_entities.block.crusher.CrusherBlock;
import net.shadowbeast.frostbound.block_entities.block.furnace.AlloyFurnaceBlock;
import net.shadowbeast.frostbound.block_entities.block.furnace.WinterFurnaceBlock;
import net.shadowbeast.frostbound.blocks.crops.KohlrabiCropBlock;
import net.shadowbeast.frostbound.blocks.crops.PepperCropBlock;
import net.shadowbeast.frostbound.blocks.custom.*;
import net.shadowbeast.frostbound.util.WoodTypesMod;
import net.shadowbeast.frostbound.worldgen.tree.FrozenTreeGrower;

import java.util.function.Supplier;

import static net.shadowbeast.frostbound.Frostbound.MOD_ID;
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);


    // Block Entities
    public static final RegistryObject<Block> WINTER_FURNACE = registerBlock("winter_furnace", ()-> new WinterFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().friction(0.9f)));
    public static final RegistryObject<Block> ALLOY_FURNACE = registerBlock("alloy_furnace", () -> new AlloyFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> CRUSHER = registerBlock("crusher", () -> new CrusherBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

    //CROPS
    public static final RegistryObject<Block> KOHLRABI_CROP = BLOCKS.register("kohlrabi_crop", () -> new KohlrabiCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));
    public static final RegistryObject<Block> PEPPER_CROP = BLOCKS.register("pepper_crop", () -> new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    //ORES
    public static final RegistryObject<Block> AQUANIUM_ORE = registerBlock("aquanium_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BONE_ORE = registerBlock("bone_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_LUMINITE_ORE = registerBlock("deepslate_luminite_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).strength(4.5F,3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_GEM_ORE = registerBlock("frozen_gem_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(4.7F,3.8F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LUMINITE_ORE = registerBlock("luminite_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NETHER_FIRERITE_ORE = registerBlock("nether_firerite_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(5.0F,4.3F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.1F,3.1F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SAND_AQUANIUM_ORE = registerBlock("sand_aquanium_ore", ()-> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND).strength(1.4F,1.2F).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(2.6F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).strength(3.2F,3.2F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BAUXITE = registerBlock("bauxite", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.2F,3.2F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    //ORES BLOCKS
    public static final RegistryObject<Block> AQUANIUM_BlOCK = registerBlock("aquanium_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FIRERITE_BlOCK = registerBlock("firerite_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_GEM_BlOCK = registerBlock("frozen_gem_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LUMINITE_BlOCK = registerBlock("luminite_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATINUM_BlOCK = registerBlock("platinum_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILVER_BlOCK = registerBlock("silver_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(3.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_BlOCK = registerBlock("steel_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_BlOCK = registerBlock("titanium_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));

    //RAW ORES
    public static final RegistryObject<Block> AQUANIUM_SHARD_BLOCK = registerBlock("aquanium_shard_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK).strength(3.0F,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    //NATURAL BLOCKS
    public static final RegistryObject<Block> FROZEN_STONE = registerBlock("frozen_stone", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.7F,6.0F)));
    public static final RegistryObject<Block> FROZEN_STONE_BRICKS = registerBlock("frozen_stone_bricks", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).strength(1.7F,6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE = registerBlock("frozen_cobblestone", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).strength(2.2F)));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE_WALL = registerBlock("frozen_cobblestone_wall", ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL)));
    public static final RegistryObject<Block> FROZEN_STONE_BRICK_WALL = registerBlock("frozen_stone_brick_wall", ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));

    //FROZEN BLOCKS
    public static final RegistryObject<Block> FROZEN_CRACKED_STONE_TILED_BRICKS = registerBlock("frozen_cracked_stone_tiled_bricks", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> FROZEN_CRACKED_STONE_TILES = registerBlock("frozen_cracked_stone_tiles", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> FROZEN_CRACKED_POLISHED_STONE = registerBlock("frozen_cracked_polished_stone", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> FROZEN_STONE_PILLAR = registerBlock("frozen_stone_pillar", ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(ModBlocks.FROZEN_STONE_BRICKS.get())));
    public static final RegistryObject<Block> FROZEN_STONE_TILES = registerBlock("frozen_stone_tiles", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> FROZEN_POLISHED_STONE = registerBlock("frozen_polished_stone", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> FROZEN_RUNIC_STONE_BRICKS = registerBlock("frozen_runic_stone_bricks", ()-> new IcyBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> ENDER_PEARL_BLOCK = registerBlock("ender_pearl_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> END_GLASS = registerBlock("end_glass", ()-> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> END_GLASS_PANE = registerBlock("end_glass_pane", ()->  new IronBarsBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<EndLanternBlock> END_LANTERN = registerBlock("end_lantern", ()-> new EndLanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((p_152677_) -> 14).noOcclusion().pushReaction(PushReaction.DESTROY)));

    //SIGNS
    public static final RegistryObject<Block> FROZEN_SIGN = BLOCKS.register("frozen_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodTypesMod.FROZEN));
    public static final RegistryObject<Block> FROZEN_WALL_SIGN = BLOCKS.register("frozen_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodTypesMod.FROZEN));
    public static final RegistryObject<Block> FROZEN_HANGING_SIGN = BLOCKS.register("frozen_hanging_sign", () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), WoodTypesMod.FROZEN));
    public static final RegistryObject<Block> FROZEN_WALL_HANGING_SIGN = BLOCKS.register("frozen_wall_hanging_sign", () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), WoodTypesMod.FROZEN));

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
    public static final RegistryObject<Block> FROZEN_SAPLING= registerBlock("frozen_sapling", ()-> new SaplingBlock(new FrozenTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    //LEAVES
    public static final RegistryObject<Block> FROZEN_LEAVES = registerBlock("frozen_leaves", ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}});


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, Rarity.COMMON);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String registryName, Supplier<T> block, Rarity rarity) {
        RegistryObject<T> registeredBlock = BLOCKS.register(registryName, block);
        BLOCK_ITEMS.register(registryName, () -> new BlockItem(registeredBlock.get(), new Item.Properties().rarity(rarity)));
        return registeredBlock;
    }
}



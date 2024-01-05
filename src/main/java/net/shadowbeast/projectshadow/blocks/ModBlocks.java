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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.costum.FusionFurnace;
import net.shadowbeast.projectshadow.blocks.costum.ModFlammableRotatedPillarBlock;
import net.shadowbeast.projectshadow.blocks.costum.WinterFurnace;
import net.shadowbeast.projectshadow.items.ModItems;

import java.util.function.Supplier;

public class  ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectShadow.MOD_ID);

    //ORES
    public static final RegistryObject<Block> AQUANIUM_ORE = registerBlock("aquanium_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(3.0f,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SAND_AQUANIUM_ORE = registerBlock("sand_aquanium_ore",
            ()-> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND)
                    .strength(1.4F,1.2F).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final RegistryObject<Block> FIRERITE_ORE = registerBlock("firerite_ore",//TODO Nether gem ore
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(5.0f,4.3F).sound(SoundType.NETHERRACK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LUMINITE_ORE = registerBlock("luminite_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(3.0f,3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_LUMINITE_ORE = registerBlock("deepslate_luminite_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(4.5f,3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(3.1f,3.1F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_EMERALD_ORE)
                    .strength(4.5f,3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(2.6f,2.8F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_ORE = registerBlock("steel_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(3.1f,3.1F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(3.2f,3.2F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENDERIUM_END_ORE = registerBlock("enderium_end_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                    .strength(5.2f,4.8F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    //Ores Blocks
    public static final RegistryObject<Block> PLATINUM_BlOCK = registerBlock("platinum_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> STEEL_BlOCK = registerBlock("steel_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TITANIUM_BlOCK = registerBlock("titanium_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUANIUM_BlOCK = registerBlock("aquanium_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LUMINITE_BlOCK = registerBlock("luminite_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUBY_BlOCK = registerBlock("ruby_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FIRERITE_BlOCK = registerBlock("firerite_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)
                    .strength(5.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILVER_BlOCK = registerBlock("silver_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)
                    .strength(3.0F,6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENDERIUM_BlOCK = registerBlock("enderium_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
                    .strength(9.0F,12.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));


    //Entities
    public static final RegistryObject<Block> FUSION_FURNACE = registerBlock("fusion_furnace",
            ()-> new FusionFurnace(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(3.5F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WINTER_FURNACE = registerBlock("winter_furnace",
            ()-> new WinterFurnace(BlockBehaviour.Properties.copy(Blocks.FURNACE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()));

    //Natural Blocks
    public static final RegistryObject<Block> FROZEN_STONE = registerBlock("frozen_stone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.7F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE = registerBlock("frozen_cobblestone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).strength(2.2F).requiresCorrectToolForDrops()));


    //WOOD
    public static final RegistryObject<Block> FROZEN_LOG = registerBlock("frozen_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> FROZEN_WOOD = registerBlock("frozen_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_FROZEN_LOG = registerBlock("stripped_frozen_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_FROZEN_WOOD = registerBlock("stripped_frozen_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> FROZEN_PRESSURE_PLATE = registerBlock("frozen_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE_WALL = registerBlock("frozen_cobblestone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL)));
    public static final RegistryObject<Block> FROZEN_DOOR = registerBlock("frozen_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR),BlockSetType.OAK));
    public static final RegistryObject<Block> FROZEN_TRAPDOOR = registerBlock("frozen_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR),BlockSetType.OAK));
    public static final RegistryObject<Block> FROZEN_BUTTON = registerBlock("frozen_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK,30,true));
    public static final RegistryObject<Block> FROZEN_STAIRS = registerBlock("frozen_stairs",
            ()-> new StairBlock(() -> ModBlocks.FROZEN_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> FROZEN_FENCE = registerBlock("frozen_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> FROZEN_FENCE_GATE = registerBlock("frozen_fence_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN,SoundEvents.FENCE_GATE_CLOSE){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> FROZEN_SLAB = registerBlock("frozen_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> FROZEN_PLANKS = registerBlock("frozen_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> FROZEN_LEAVES = registerBlock("frozen_leaves",
            ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> FROZEN_SAPLING= registerBlock("frozen_sapling",
            ()-> new SaplingBlock(null,BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).requiresCorrectToolForDrops()));










    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

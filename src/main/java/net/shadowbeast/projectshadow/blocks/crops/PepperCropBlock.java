package net.shadowbeast.projectshadow.blocks.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.shadowbeast.projectshadow.items.ModItems;
import org.jetbrains.annotations.NotNull;


public class PepperCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    private static final VoxelShape[] SHAPE_BY_AGE;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
    public PepperCropBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.PEPPER_SEEDS.get();
    }
    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    static{
        SHAPE_BY_AGE = new VoxelShape[]{
                Block.box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
                Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0),
                Block.box(4.0, 0.0, 4.0, 12.0, 9.0, 12.0),
                Block.box(3.0, 0.0, 3.0, 13.0, 12.0, 13.0),
                Block.box(3.0, 0.0, 3.0, 13.0, 15.0, 13.0),
                Block.box(3.0, 0.0, 3.0, 13.0, 15.0, 13.0),
                Block.box(3.0, 0.0, 3.0, 13.0, 15.0, 13.0),
                Block.box(3.0, 0.0, 3.0, 13.0, 15.0, 13.0)
        };
    }
}

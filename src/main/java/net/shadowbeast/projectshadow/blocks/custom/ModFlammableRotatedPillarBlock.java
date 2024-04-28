package net.shadowbeast.projectshadow.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    boolean icy;
    public ModFlammableRotatedPillarBlock(Properties pProperties, boolean pIsIcy) {
        super(pProperties);
        this.icy = pIsIcy;
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return !this.icy;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (this.icy) {
            return 0;
        }
        return 5;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (this.icy) {
            return 0;
        }
        return 5;
    }
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.FROZEN_LOG.get())){
                return ModBlocks.STRIPPED_FROZEN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlocks.FROZEN_WOOD.get())){
                return ModBlocks.STRIPPED_FROZEN_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
            return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    @Override
    public void onRemove(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pNewState, boolean pMovedByPiston) {
        if(this.icy) pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 0.1f, 1f, 16);
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
}

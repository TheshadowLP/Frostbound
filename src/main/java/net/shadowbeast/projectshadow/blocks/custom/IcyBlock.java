package net.shadowbeast.projectshadow.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class IcyBlock extends Block {
    public IcyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, 1F, 16);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 0.3f, 1F, 16);
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
}

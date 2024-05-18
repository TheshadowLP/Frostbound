package net.shadowbeast.projectshadow.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class EndLavaBlock extends LiquidBlock {
    public EndLavaBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        entity.setSecondsOnFire(15);
    }
}

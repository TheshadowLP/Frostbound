package net.shadowbeast.arcanemysteries.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.NotNull;

public class EndLavaBlock extends LiquidBlock {
    public EndLavaBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }
    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, Entity entity) {
        entity.setSecondsOnFire(15);
    }
}

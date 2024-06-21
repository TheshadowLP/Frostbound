package net.shadowbeast.arcanemysteries.fluid.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.shadowbeast.arcanemysteries.registries.ModBlocks;

public abstract class EndFlowingFluid extends ForgeFlowingFluid {

    protected EndFlowingFluid(Properties properties) {
        super(properties);
    }
 
    @Override
    protected void spread(Level world, BlockPos pos, FluidState fluidState) {
        super.spread(world, pos, fluidState);

        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = pos.relative(direction);
            FluidState adjacentFluidState = world.getFluidState(adjacentPos);
            if (adjacentFluidState.isSource() && adjacentFluidState.getType() == Fluids.WATER) {
                if (world.getBlockState(pos).getBlock() != ModBlocks.END_OBSIDIAN.get()) {
                    world.setBlockAndUpdate(pos, ModBlocks.END_OBSIDIAN.get().defaultBlockState());
                }
                break;
            }
        }
    }

    public static class Flowing extends EndFlowingFluid {

        public Flowing(Properties properties) {
            super(properties);
            this.registerDefaultState(this.defaultFluidState().setValue(LEVEL,7));
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

    }

    public static class Source extends EndFlowingFluid {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

    }
}

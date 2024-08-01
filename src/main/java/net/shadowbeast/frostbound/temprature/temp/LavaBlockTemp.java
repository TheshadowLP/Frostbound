package net.shadowbeast.frostbound.temprature.temp;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.shadowbeast.frostbound.temprature.Temperature;
import net.shadowbeast.frostbound.util.MathHelper;

public class LavaBlockTemp extends BlockTemp
{
    public LavaBlockTemp()
    {   super(Blocks.LAVA);
    }

    @Override
    public double getTemperature(Level level, LivingEntity entity, BlockState state, BlockPos pos, double distance)
    {   FluidState fluidState = state.getFluidState();
        double temp = (fluidState.getAmount()/7f) / (entity.getVehicle() instanceof Strider ? 50d : 3d);
        return MathHelper.blend(temp, 0, distance, 0.5, 7);
    }

    @Override
    public double maxEffect() {
        return Temperature.convert(300, Temperature.Units.F, Temperature.Units.MC, false);
    }

    @Override
    public double maxTemperature() {
        return Temperature.convert(1000, Temperature.Units.F, Temperature.Units.MC, true);
    }
}

package net.shadowbeast.arcanemysteries.blocks.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class NewTargetBlock extends Block {
    private static final IntegerProperty OUTPUT_POWER;
    private static final int ACTIVATION_TICKS_ARROWS = 20;
    private static final int ACTIVATION_TICKS_OTHER = 8;
    public NewTargetBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OUTPUT_POWER, 0));
    }
    public void onProjectileHit(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockHitResult pHit, @NotNull Projectile pProjectile) {
        int $$4 = updateRedstoneOutput(pLevel, pState, pHit, pProjectile);
        Entity $$5 = pProjectile.getOwner();
        if ($$5 instanceof ServerPlayer $$6) {
            $$6.awardStat(Stats.TARGET_HIT);
            CriteriaTriggers.TARGET_BLOCK_HIT.trigger($$6, pProjectile, pHit.getLocation(), $$4);
        }
    }
    private static int updateRedstoneOutput(LevelAccessor pLevel, BlockState pState, BlockHitResult pHit, Entity pProjectile) {
        int $$4 = getRedstoneStrength(pHit, pHit.getLocation());
        int $$5 = pProjectile instanceof AbstractArrow ? 20 : 8;
        if (!pLevel.getBlockTicks().hasScheduledTick(pHit.getBlockPos(), pState.getBlock())) {
            setOutputPower(pLevel, pState, $$4, pHit.getBlockPos(), $$5);
        }
        return $$4;
    }
    private static int getRedstoneStrength(BlockHitResult pHit, Vec3 pHitLocation) {
        Direction $$2 = pHit.getDirection();
        double $$3 = Math.abs(Mth.frac(pHitLocation.x) - 0.5);
        double $$4 = Math.abs(Mth.frac(pHitLocation.y) - 0.5);
        double $$5 = Math.abs(Mth.frac(pHitLocation.z) - 0.5);
        Direction.Axis $$6 = $$2.getAxis();
        double $$9;
        if ($$6 == Direction.Axis.Y) {
            $$9 = Math.max($$3, $$5);
        } else if ($$6 == Direction.Axis.Z) {
            $$9 = Math.max($$3, $$4);
        } else {
            $$9 = Math.max($$4, $$5);
        }
        return Math.max(1, Mth.ceil(15.0 * Mth.clamp((0.5 - $$9) / 0.5, 0.0, 1.0)));
    }
    private static void setOutputPower(LevelAccessor pLevel, BlockState pState, int pPower, BlockPos pPos, int pWaitTime) {
        pLevel.setBlock(pPos, (BlockState)pState.setValue(OUTPUT_POWER, pPower), 3);
        pLevel.scheduleTick(pPos, pState.getBlock(), pWaitTime);
    }
    public void tick(BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if ((Integer)pState.getValue(OUTPUT_POWER) != 0) {
            pLevel.setBlock(pPos, (BlockState)pState.setValue(OUTPUT_POWER, 0), 3);
        }
    }
    public int getSignal(BlockState pBlockState, @NotNull BlockGetter pBlockAccess, @NotNull BlockPos pPos, @NotNull Direction pSide) {
        return (Integer)pBlockState.getValue(OUTPUT_POWER);
    }
    public boolean isSignalSource(BlockState pState) {
        return true;
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{OUTPUT_POWER});
    }
    public void onPlace(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide() && !pState.is(pOldState.getBlock())) {
            if ((Integer)pState.getValue(OUTPUT_POWER) > 0 && !pLevel.getBlockTicks().hasScheduledTick(pPos, this)) {
                pLevel.setBlock(pPos, (BlockState)pState.setValue(OUTPUT_POWER, 0), 18);
            }

        }
    }
    static {
        OUTPUT_POWER = BlockStateProperties.POWER;
    }
}


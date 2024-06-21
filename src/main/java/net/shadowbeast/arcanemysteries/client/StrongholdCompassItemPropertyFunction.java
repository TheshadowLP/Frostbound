package net.shadowbeast.arcanemysteries.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StrongholdCompassItemPropertyFunction implements ClampedItemPropertyFunction {
    private static final int DEFAULT_ROTATION = 0;
    private final CompassWobble wobble = new CompassWobble();
    private final CompassWobble wobbleRandom = new CompassWobble();
    private final CompassTarget compassTarget;

    public StrongholdCompassItemPropertyFunction(CompassTarget compassTarget) {
        this.compassTarget = compassTarget;
    }

    @Override
    public float unclampedCall(@NotNull ItemStack stack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity entity, int seed) {
        Entity effectiveEntity = entity != null ? entity : stack.getEntityRepresentation();
        if (effectiveEntity == null) {
            return 0.0F;
        } else {
            ClientLevel level = tryFetchLevelIfMissing(effectiveEntity, clientLevel);
            if (level == null) {
                return 0.0F;
            } else {
                assert clientLevel != null;
                return getCompassRotation(stack, clientLevel, seed, effectiveEntity);
            }
        }
    }

    private float getCompassRotation(ItemStack stack, ClientLevel level, int seed, Entity entity) {
        GlobalPos globalPos = compassTarget.getPos(level, stack, entity);
        long gameTime = level.getGameTime();
        if (!isValidCompassTargetPos(entity, globalPos)) {
            return getRandomlySpinningRotation(seed, gameTime);
        } else {
            return getRotationTowardsCompassTarget(entity, gameTime, globalPos.pos());
        }
    }

    private float getRandomlySpinningRotation(int seed, long ticks) {
        if (wobbleRandom.shouldUpdate(ticks)) {
            wobbleRandom.update(ticks, Math.random());
        }
        double rotation = wobbleRandom.rotation + ((float) hash(seed) / (float) Integer.MAX_VALUE);
        return Mth.positiveModulo((float) rotation, 1.0F);
    }

    private float getRotationTowardsCompassTarget(Entity entity, long ticks, BlockPos pos) {
        double angleToPos = getAngleFromEntityToPos(entity, pos);
        double visualRotationY = getWrappedVisualRotationY(entity);

        if (entity instanceof Player player && player.isLocalPlayer()) {
            if (wobble.shouldUpdate(ticks)) {
                wobble.update(ticks, 0.5D - (visualRotationY - 0.25D));
            }
            double d3 = angleToPos + wobble.rotation;
            return Mth.positiveModulo((float) d3, 1.0F);
        }

        double rotation = 0.5D - (visualRotationY - 0.25D - angleToPos);
        return Mth.positiveModulo((float) rotation, 1.0F);
    }

    @Nullable
    private ClientLevel tryFetchLevelIfMissing(Entity entity, @Nullable ClientLevel level) {
        return level == null && entity.level() instanceof ClientLevel ? (ClientLevel) entity.level() : level;
    }

    private boolean isValidCompassTargetPos(Entity entity, @Nullable GlobalPos pos) {
        return pos != null && pos.dimension() == entity.level().dimension() &&
                !(pos.pos().distToCenterSqr(entity.position()) < 1.0E-5D);
    }

    private double getAngleFromEntityToPos(Entity entity, BlockPos pos) {
        return Math.atan2(pos.getZ() - entity.getZ(), pos.getX() - entity.getX()) / (Math.PI * 2.0D);
    }

    private double getWrappedVisualRotationY(Entity entity) {
        return Mth.wrapDegrees(entity.getYRot()) / 360.0D;
    }

    private int hash(int value) {
        return value * 1327217883;
    }

    public interface CompassTarget {
        @Nullable
        GlobalPos getPos(ClientLevel level, ItemStack stack, Entity entity);
    }

    static class CompassWobble {
        double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        boolean shouldUpdate(long ticks) {
            return this.lastUpdateTick != ticks;
        }

        void update(long ticks, double rotation) {
            this.lastUpdateTick = ticks;
            double d0 = rotation - this.rotation;
            d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += d0 * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }
}

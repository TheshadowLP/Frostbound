package net.shadowbeast.projectshadow.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.shadowbeast.projectshadow.items.ModItems;

@OnlyIn(Dist.CLIENT)
public class StrongholdCompassItemPropertyFunction implements ClampedItemPropertyFunction {
    public static final Item COMPASS_ITEM = ModItems.STRONGHOLD_COMPASS.get();
    private static final double DEG_TO_RAD = 0.01745329238474369D;
    private double rotation;
    private double deltaRotation;
    private long lastUpdateTick;

    @Override
    public float unclampedCall(ItemStack pStack, ClientLevel pLevel, LivingEntity pEntity, int pSeed) {
        if (pEntity == null && !pStack.isFramed()) {
            return 0;
        } else {
            boolean entityExists = pEntity != null;
            Entity entity = entityExists ? pEntity : pStack.getFrame();
            if (pLevel == null && entity.level() instanceof ClientLevel) {
                pLevel = (ClientLevel) entity.level();
            }

            double rotation = entityExists ? (double) entity.getYRot() : getItemFrameRotation((ItemFrame) entity);
            rotation = rotation % 360;
            double adjusted = Math.PI - ((rotation - 90) * DEG_TO_RAD - getAngle(pLevel, entity, pStack));

            if (entityExists) {
                adjusted = getRandomlySpinningRotation(pLevel, adjusted);
            }

            float f = (float) (adjusted / (Math.PI * 2));
            return Mth.positiveModulo(f, 1);
        }
    }

    private double getRandomlySpinningRotation(ClientLevel pLevel, double pAmount) {
        long gameTime = pLevel.getGameTime();
        if (gameTime != lastUpdateTick) {
            lastUpdateTick = gameTime;
            double d0 = pAmount - rotation;
            d0 = Mth.positiveModulo(d0 + Math.PI, Math.PI * 2) - Math.PI;
            d0 = Mth.clamp(d0, -1, 1);
            deltaRotation += d0 * 0.1;
            deltaRotation *= 0.8;
            rotation += deltaRotation;
        }

        return rotation;
    }

    private double getItemFrameRotation(ItemFrame itemFrame) {
        Direction direction = itemFrame.getDirection();
        int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
        return Mth.wrapDegrees(180 + direction.get2DDataValue() * 90 + itemFrame.getRotation() * 45 + i);
    }

    private double getAngle(ClientLevel pLevel, Entity pEntity, ItemStack pStack) {
        if (pStack.getItem() == COMPASS_ITEM) {
            // Block position you are looking for, currently set to 0 0 (y value is always ignored)
            BlockPos blockPos = new BlockPos(0, 0, 0);

            // Calculates the direction (in radians) from entity's position to the block position
            return Math.atan2((double) blockPos.getZ() - pEntity.position().z(), (double) blockPos.getX() - pEntity.position().x());
        }
        return 0;
    }
}

package net.shadowbeast.arcanemysteries.interfaces.util;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public interface IRoastedEntity
{
    int BASE_TICKS_REQUIRED_TO_ROAST = 140;
    int ROAST_HURT_FREQUENCY = 40;
    EntityDataAccessor<Integer> DATA_TICKS_ROASTED = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.INT);

    int getTicksRoasted();
    void setTicksRoasted(int pTicksFrozen);
    float getPercentRoasted();
    boolean isFullyRoasted();

    default boolean canRoast() {
        return true;
    }

    default int getTicksRequiredToRoast() {
        return 140;
    }
}

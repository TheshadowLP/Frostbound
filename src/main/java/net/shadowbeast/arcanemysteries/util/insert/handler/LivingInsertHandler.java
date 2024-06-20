package net.shadowbeast.arcanemysteries.util.insert.handler;

import net.minecraft.world.entity.LivingEntity;
import net.shadowbeast.arcanemysteries.util.insert.InsertHandler;

public class LivingInsertHandler extends InsertHandler<LivingInsertHandler.LivingInsert> {
    public void insert(LivingEntity entity) {
        loopThrough(instance -> instance.apply(entity));
    }

    public static interface LivingInsert extends InsertHandler.Insert {
        void apply(LivingEntity param1LivingEntity);
    }
}


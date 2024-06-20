package net.shadowbeast.arcanemysteries.util.insert.handler;


import net.minecraft.world.level.LevelAccessor;
import net.shadowbeast.arcanemysteries.util.insert.InsertHandler;

public class LevelHandler extends InsertHandler<LevelHandler.LevelInsert> {
    public void insert(LevelAccessor level) {
        loopThrough(instance -> instance.apply(level));
    }

    public static interface LevelInsert extends InsertHandler.Insert {
        void apply(LevelAccessor param1LevelAccessor);
    }
}

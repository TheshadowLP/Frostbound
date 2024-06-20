package net.shadowbeast.arcanemysteries.util.insert.handler;

import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.util.insert.InsertHandler;

public class PlayerInsertHandler extends InsertHandler<PlayerInsertHandler.PlayerInsert> {
    public void insert(Player player) {
        loopThrough(instance -> instance.apply(player));
    }

    public static interface PlayerInsert extends InsertHandler.Insert {
        void apply(Player param1Player);
    }
}

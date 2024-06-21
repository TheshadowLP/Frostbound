package net.shadowbeast.arcanemysteries.util.insert;

import java.util.function.Consumer;

public abstract class InsertHandler<T extends InsertHandler.Insert> {
    public final void loopThrough(Consumer<? super T> action) {
        InsertSystem.getInserts(this).forEach(action);
    }

    public boolean hasInserts() {
        return (!InsertSystem.getInserts(this).isEmpty());
    }

    public static interface Insert {}
}

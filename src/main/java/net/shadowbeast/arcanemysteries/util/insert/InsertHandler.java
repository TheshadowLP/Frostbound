package net.shadowbeast.arcanemysteries.util.insert;

import java.util.function.Consumer;

public abstract class InsertHandler<T extends InsertHandler.Insert> {
    public final void loopThrough(Consumer<? super T> action) {
        InsertSystem.<T>getInserts(this).forEach(action);
    }

    public boolean hasInserts() {
        return (InsertSystem.<T>getInserts(this).size() > 0);
    }

    public static interface Insert {}
}

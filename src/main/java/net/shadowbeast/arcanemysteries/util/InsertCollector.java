package net.shadowbeast.arcanemysteries.util;

import net.shadowbeast.arcanemysteries.util.insert.InsertHandler;

public interface InsertCollector {
    <T extends InsertHandler.Insert> void addInsert(InsertHandler<T> paramInsertHandler, T paramT);
}


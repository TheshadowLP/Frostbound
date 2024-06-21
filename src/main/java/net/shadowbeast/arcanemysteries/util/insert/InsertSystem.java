package net.shadowbeast.arcanemysteries.util.insert;

import com.google.common.collect.Lists;
import net.shadowbeast.arcanemysteries.util.InsertCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertSystem implements InsertCollector {
    public static final InsertSystem instance = new InsertSystem();

    private static final Map<InsertHandler<? extends InsertHandler.Insert>, List<? extends InsertHandler.Insert>> INSERTS = new HashMap<>();

    protected static <T extends InsertHandler.Insert> List<T> getInserts(InsertHandler<T> handler) {
        if (!INSERTS.containsKey(handler))
            return new ArrayList<>();
        return (List<T>) Lists.newArrayList(INSERTS.get(handler));
    }

    public <T extends InsertHandler.Insert> void addInsert(InsertHandler<T> handler, T insert) {
        if (!INSERTS.containsKey(handler))
            INSERTS.put(handler, new ArrayList<>());
        List<T> list = getInserts(handler);
        list.add(insert);
        INSERTS.put(handler, list);
    }
}

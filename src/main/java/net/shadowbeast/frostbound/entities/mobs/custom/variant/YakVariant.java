package net.shadowbeast.frostbound.entities.mobs.custom.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum YakVariant {
    BLACK(0),
    BROWN(1),
    WHITE(2),
    GRAY(3);

    private static final YakVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(YakVariant::getId)).toArray(YakVariant[]::new);
    private final int id;

    YakVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static YakVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

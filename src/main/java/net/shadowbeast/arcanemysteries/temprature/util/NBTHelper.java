package net.shadowbeast.arcanemysteries.temprature.util;


import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;

public class NBTHelper {
    public static ListTag newDoubleNBTList(double... numbers) {
        ListTag listnbt = new ListTag();
        for (double d0 : numbers)
            listnbt.add(DoubleTag.valueOf(d0));
        return listnbt;
    }

    public class NbtType {
        public static final int IntNBT = 3;

        public static final int CompoundNBT = 10;
    }
}

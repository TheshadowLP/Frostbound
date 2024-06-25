package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.advancements.CriteriaTriggers;
import net.shadowbeast.arcanemysteries.advancement.MilkCowWithBottle;
import net.shadowbeast.arcanemysteries.advancement.UseCrusher;
import net.shadowbeast.arcanemysteries.advancement.UsingStaff;

public class CriteriaTriggerRegistry {
    public static final UsingStaff USING_STAFF = new UsingStaff();
    public static final UseCrusher USE_CRUSHER = new UseCrusher();
    public static final MilkCowWithBottle MILK_COW_WITH_BOTTLE = new MilkCowWithBottle();

    public static void registerCriteriaTriggers() {
        CriteriaTriggers.register(USING_STAFF);
        CriteriaTriggers.register(MILK_COW_WITH_BOTTLE);
        CriteriaTriggers.register(USE_CRUSHER);
    }
}

package net.shadowbeast.frostbound.registries;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.advancement.UsingStaff;

public class CriteriaTriggerRegistry {
    public static final UsingStaff USING_STAFF = new UsingStaff();
    public static final PlayerTrigger USE_CRUSHER = new PlayerTrigger(new ResourceLocation(Frostbound.MOD_ID,"use_crusher"));
    public static final PlayerTrigger MILK_COW_WITH_BOTTLE = new PlayerTrigger(new ResourceLocation(Frostbound.MOD_ID,"milk_cow_with_bottle"));

    public static void registerCriteriaTriggers() {
        CriteriaTriggers.register(USING_STAFF);
        CriteriaTriggers.register(MILK_COW_WITH_BOTTLE);
        CriteriaTriggers.register(USE_CRUSHER);
    }
}

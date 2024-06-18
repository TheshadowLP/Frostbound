package net.shadowbeast.arcanemysteries.temprature.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.temprature.TemperatureData;

public class EStats
{
    public static String temperatureStatsID = "TemperatureStats";

    public static TemperatureData getTemperatureStats(LivingEntity entity) {
        TemperatureData stats = new TemperatureData();
        if(entity != null) {
            if (getModNBT(entity) != null && getModNBT(entity).contains(temperatureStatsID, 10)) {
                stats.read(getModNBT(entity).getCompound(temperatureStatsID));
                return stats;
            }
        }
        return stats;
    }
    public static void setTemperatureStats(Entity entity, TemperatureData temperatureStats) {
        CompoundTag compound2 = new CompoundTag();
        temperatureStats.write(compound2);
        getModNBT(entity).put(temperatureStatsID, compound2);
    }
    public static void addStatsOnSpawn(Player player) {
        if (player != null) {
            CompoundTag compound;
            compound = getOrCreateModNBT(player);
            String name = player.getScoreboardName();
            if(player.isAlive()) {

                if (!compound.contains(temperatureStatsID)) {
                    setTemperatureStats(player, new TemperatureData());
                }
            }
        }
    }
    private static String append(String string) {
        return ArcaneMysteries.MOD_ID+":"+string;
    }

    public static String getModDataString() {
        return ArcaneMysteries.MOD_ID+":PlayerData";
    }

    public static CompoundTag getModNBT(Entity entity) {
        return entity.getPersistentData().getCompound(getModDataString());
    }

    public static CompoundTag getOrCreateModNBT(Entity entity) {
        if (!entity.getPersistentData().contains(getModDataString(), 10)) {
            entity.getPersistentData().put(getModDataString(), new CompoundTag());
        }
        return entity.getPersistentData().getCompound(getModDataString());
    }

    public static void setModNBT(CompoundTag nbt, Entity entity) {
        entity.getPersistentData().put(getModDataString(), nbt);
    }
}

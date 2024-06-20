package net.shadowbeast.arcanemysteries.temprature.util;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Map;

@FunctionalInterface
public interface TemperatureQuery {
    public static Map<ResourceLocation,Tuple<TemperatureQuery, TemperatureModifier.ContributingFactor>> queries = Maps.newHashMap();
    public static void registerQuery(ResourceLocation id, TemperatureModifier.ContributingFactor factor, TemperatureQuery query) {
        queries.put(id, new Tuple<>(query, factor));
    }
    public static void registerQuery(String id, TemperatureModifier.ContributingFactor factor, TemperatureQuery query) {
        registerQuery(new ResourceLocation(id), factor, query);
    }

    double run(@Nullable Player player, double temp, Level level, BlockPos pos, boolean applyTemp);
}
package net.shadowbeast.arcanemysteries.json;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.shadowbeast.arcanemysteries.util.BiomeJsonHolder;

import java.util.Map;
import java.util.UUID;

public class DataMaps
{
    public static class Server {
        public static final Map<UUID, Boolean> syncedClients = Maps.newHashMap();
        public static final Map<ResourceLocation, EntityTemperatureJsonHolder> entityTemperature = Maps.newHashMap();
        public static final Map<ResourceLocation, BiomeJsonHolder> biome = Maps.newHashMap();
    }
    @OnlyIn(Dist.CLIENT)
    public static class Client {
        public static ImmutableMap<ResourceLocation, EntityTemperatureJsonHolder> entityTemperature = ImmutableMap.of();
        public static ImmutableMap<ResourceLocation, BiomeJsonHolder> biome = ImmutableMap.of();
    }
}

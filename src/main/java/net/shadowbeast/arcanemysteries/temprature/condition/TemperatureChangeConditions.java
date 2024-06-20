package net.shadowbeast.arcanemysteries.temprature.condition;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegisterEvent;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

import java.util.HashMap;
import java.util.Map;

public class TemperatureChangeConditions
{
    public static final Map<ResourceLocation, TemperatureChangeCondition<?>> CONDITION_LIST = new HashMap<ResourceLocation, TemperatureChangeCondition<?>>();

    public static final TemperatureChangeCondition<?> DEFAULT = register("default", new DefaultCondition());
    public static final TemperatureChangeCondition<?> BIOME = register("biome", new BiomeCondition());
    public static final TemperatureChangeCondition<?> NOT_BIOME = register("not_biome", new BiomeNotCondition());

    public static TemperatureChangeCondition<?> register(String name, TemperatureChangeCondition<?> condition) {
        CONDITION_LIST.put(ArcaneMysteries.getInstance().location(name), condition);
        return condition;
    }

    public static void registerAll(RegisterEvent.RegisterHelper<TemperatureChangeCondition<?>> registry) {
        for(Map.Entry<ResourceLocation, TemperatureChangeCondition<?>> condition : CONDITION_LIST.entrySet()) {
            registry.register(condition.getKey(), condition.getValue());

        }

    }
}

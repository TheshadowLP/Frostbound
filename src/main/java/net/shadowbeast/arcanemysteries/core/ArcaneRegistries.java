package net.shadowbeast.arcanemysteries.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.shadowbeast.arcanemysteries.temprature.condition.TemperatureChangeCondition;



public class ArcaneRegistries
{
    public static final ResourceKey<Registry<TemperatureChangeCondition<?>>> CONDITION = key("arcanemysteries:temperature_change_condition");
    private static <T> ResourceKey<Registry<T>> key(String name)
    {
        return ResourceKey.createRegistryKey(new ResourceLocation(name));
    }
    public class ForgeRegistry {
        public static final IForgeRegistry<TemperatureChangeCondition<?>> CONDITION = RegistryManager.ACTIVE.getRegistry(ArcaneRegistries.CONDITION);
    }

}

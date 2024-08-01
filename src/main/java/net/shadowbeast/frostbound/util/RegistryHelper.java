package net.shadowbeast.frostbound.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class RegistryHelper
{
    public static boolean matchesRegistryKey(ResourceLocation name, ResourceKey<?> key) {
        return name.toString().equalsIgnoreCase(key.registry().toString());
    }
}

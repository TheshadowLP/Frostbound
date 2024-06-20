package net.shadowbeast.arcanemysteries.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface  ADamageTypes {
    ResourceKey<DamageType> HYPOTHERMIA = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("arcanemysteries:hypothermia"));
    ResourceKey<DamageType> HYPERTHERMIA = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("arcanemysteries:hyperthermia"));
    ResourceKey<DamageType> ROAST = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("arcanemysteries:roast"));
}

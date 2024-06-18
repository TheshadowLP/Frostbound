package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

@SuppressWarnings("deprecation")
public class FoodListRegistry {
    public static final FoodProperties KOHLRABI = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.7F).build();
    public static final FoodProperties PEPPER = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.5F).effect(new MobEffectInstance(MobEffects.HUNGER,200,0),0.3F).build();
    public static final FoodProperties STACKED_POTATO = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).build();
    public static final FoodProperties STACKED_BAKED_POTATO = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.8F).build();
    public static final FoodProperties RAW_YAK_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5f).build();
    public static final FoodProperties COOKED_YAK_MEAT = (new FoodProperties.Builder()).nutrition(9).saturationMod(1f).build();
}

package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.effect.FreezeEffect;
import net.shadowbeast.arcanemysteries.effect.MobEffectS;

public class EffectsRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ArcaneMysteries.MOD_ID);
    public static final RegistryObject<MobEffect> FREEZE = EFFECTS.register("freeze",
            () -> new FreezeEffect(MobEffectCategory.HARMFUL, 3124687));
    public static final RegistryObject<MobEffect> DEPRECIATED_HYPERTHERMIA = EFFECTS.register("depreciated_hyperthermia",
            () -> new MobEffectS(MobEffectCategory.HARMFUL, 3124687));
    public static final RegistryObject<MobEffect> DEPRECIATED_HYPOTHERMIA = EFFECTS.register("depreciated_hypothermia",
            () -> new MobEffectS(MobEffectCategory.HARMFUL, 3124687));

    public static final RegistryObject<MobEffect> CHILLED = EFFECTS.register("chilled",
            ()->new MobEffectS(MobEffectCategory.BENEFICIAL, 5750248));

    public static final RegistryObject<MobEffect> HEATED = EFFECTS.register("heated",
            ()->new MobEffectS(MobEffectCategory.BENEFICIAL, 16750592));
    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }


}

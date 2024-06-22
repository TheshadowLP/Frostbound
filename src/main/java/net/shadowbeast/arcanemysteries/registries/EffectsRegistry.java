package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.effect.FreezeEffect;
import net.shadowbeast.arcanemysteries.effect.MobEffectS;
import net.shadowbeast.arcanemysteries.effect.MudEffect;

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

    public static final RegistryObject<MobEffect> MUD_EFFECT = EFFECTS.register("mud_effect",
            ()-> new MudEffect(MobEffectCategory.HARMFUL, 9154528).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> HEATED = EFFECTS.register("heated",
            ()->new MobEffectS(MobEffectCategory.BENEFICIAL, 16750592));

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}

package net.shadowbeast.projectshadow.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ProjectShadow.MOD_ID);

    // pUuid is from slowness thingy. Just keep it
    public static final RegistryObject<MobEffect> MUDDIED_EYES = MOB_EFFECTS.register("muddied_eyes",
            () -> new MuddiedEyesEffect(MobEffectCategory.HARMFUL, 3124687)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890",
                            -0.30F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

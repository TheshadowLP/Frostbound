package net.shadowbeast.projectshadow.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ProjectShadow.MOD_ID);

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}

package net.shadowbeast.projectshadow.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectShadow.MOD_ID);

    public static final RegistryObject<SoundEvent> MILKING_SOUND_BOTTLE = registerSoundEvents("milking_sound_bottle");

    public static final RegistryObject<SoundEvent> LEVITATION_STAFF = registerSoundEvents("levitation_staff");
    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ProjectShadow.MOD_ID, name)));
    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

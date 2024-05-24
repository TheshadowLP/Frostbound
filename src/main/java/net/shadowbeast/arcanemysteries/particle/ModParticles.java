package net.shadowbeast.arcanemysteries.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ArcaneMysteries.MOD_ID);
    public static final RegistryObject<SimpleParticleType> FEATHER_PARTICLES =
            PARTICLE_TYPES.register("feather_particles", () -> new SimpleParticleType(true));
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
    event.registerSpriteSet(ModParticles.FEATHER_PARTICLES.get(), FeatherParticles.Provider::new);
    }
    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}

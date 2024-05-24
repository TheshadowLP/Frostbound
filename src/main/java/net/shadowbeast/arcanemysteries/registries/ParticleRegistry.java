package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.particle.FeatherParticles;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MODID;

@Mod.EventBusSubscriber(modid = ArcaneMysteries.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final RegistryObject<SimpleParticleType> FEATHER_PARTICLES = PARTICLES.register("feather_particles", () -> new SimpleParticleType(false));
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.FEATHER_PARTICLES.get(), FeatherParticles.Provider::new);
    }
}

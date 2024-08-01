package net.shadowbeast.frostbound.events;

import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.client.ModelLayers;
import net.shadowbeast.frostbound.entities.mobs.client.DungeonIceModel;
import net.shadowbeast.frostbound.entities.mobs.client.YakModel;
import net.shadowbeast.frostbound.particle.FeatherParticles;
import net.shadowbeast.frostbound.registries.EntityRegistry;
import net.shadowbeast.frostbound.registries.ParticleRegistry;

@Mod.EventBusSubscriber(modid = Frostbound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventClientBusEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(EntityRegistry.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(EntityRegistry.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayers.DUNGEONICE_LAYER, DungeonIceModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.YAK, YakModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.FEATHER_PARTICLES.get(), FeatherParticles.Provider::new);
    }


    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {

    }
}
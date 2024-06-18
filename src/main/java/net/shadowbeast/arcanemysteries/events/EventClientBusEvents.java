package net.shadowbeast.arcanemysteries.events;

import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.client.ModelLayers;
import net.shadowbeast.arcanemysteries.entities.mobs.client.DungeonIceModel;
import net.shadowbeast.arcanemysteries.entities.mobs.client.YakModel;
import net.shadowbeast.arcanemysteries.particle.FeatherParticles;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.registries.ParticleRegistry;
import net.shadowbeast.arcanemysteries.temprature.TempratureHudOverlay;

@Mod.EventBusSubscriber(modid = ArcaneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
        event.registerAboveAll("thirst", TempratureHudOverlay.HUD_TEMPERATURE);
    }
}
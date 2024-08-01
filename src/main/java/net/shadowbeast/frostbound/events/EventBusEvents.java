package net.shadowbeast.frostbound.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.client.ModelLayers;
import net.shadowbeast.frostbound.entities.mobs.custom.DungeonIceEntity;
import net.shadowbeast.frostbound.entities.mobs.custom.YakEntity;
import net.shadowbeast.frostbound.registries.EntityRegistry;

@Mod.EventBusSubscriber(modid = Frostbound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayers.FROZEN_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayers.FROZEN_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.DUNGEON_ICE.get(), DungeonIceEntity.createAttributes().build());
        event.put(EntityRegistry.YAK.get(), YakEntity.createAttributes().build());
    }


}

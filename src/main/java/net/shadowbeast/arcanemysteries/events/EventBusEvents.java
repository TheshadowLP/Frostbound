package net.shadowbeast.arcanemysteries.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.client.ModelLayers;
import net.shadowbeast.arcanemysteries.entities.mobs.custom.DungeonIceEntity;
import net.shadowbeast.arcanemysteries.entities.mobs.custom.YakEntity;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.temprature.util.EStats;

@Mod.EventBusSubscriber(modid = ArcaneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

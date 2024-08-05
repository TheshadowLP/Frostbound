package net.shadowbeast.frostbound.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.frostbound.Frostbound;

public class ModelLayers {
    public static final ModelLayerLocation FROZEN_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(Frostbound.MOD_ID, "boat/frozen"), "main");
    public static final ModelLayerLocation FROZEN_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(Frostbound.MOD_ID, "chest_boat/frozen"), "main");
    public static final ModelLayerLocation DUNGEONICE_LAYER = new ModelLayerLocation(
            new ResourceLocation(Frostbound.MOD_ID, "dungeonice_layer"), "main");
    public static final ModelLayerLocation YAK = new ModelLayerLocation(
            new ResourceLocation(Frostbound.MOD_ID, "yak_layer"), "main");
}

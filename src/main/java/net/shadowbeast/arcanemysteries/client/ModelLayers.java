package net.shadowbeast.arcanemysteries.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
public class ModelLayers {
    public static final ModelLayerLocation FROZEN_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MODID, "boat/frozen"), "main");
    public static final ModelLayerLocation FROZEN_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MODID, "chest_boat/frozen"), "main");
    public static final ModelLayerLocation DUNGEONICE_LAYER = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MODID, "dungeonice_layer"), "main");

}

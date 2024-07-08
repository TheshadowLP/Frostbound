package net.shadowbeast.arcanemysteries.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class ModelLayers {
    public static final ModelLayerLocation FROZEN_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MOD_ID, "boat/frozen"), "main");
    public static final ModelLayerLocation FROZEN_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MOD_ID, "chest_boat/frozen"), "main");
    public static final ModelLayerLocation DUNGEONICE_LAYER = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MOD_ID, "dungeonice_layer"), "main");
    public static final ModelLayerLocation YAK = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MOD_ID, "yak_layer"), "main");
    public static final ModelLayerLocation ENDER_ELYTRA = new ModelLayerLocation(
            new ResourceLocation(ArcaneMysteries.MOD_ID, "ender_elytra"), "left_wing");
}

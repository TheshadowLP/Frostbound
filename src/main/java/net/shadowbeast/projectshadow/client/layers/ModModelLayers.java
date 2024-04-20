package net.shadowbeast.projectshadow.client.layers;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModModelLayers {
    public static final ModelLayerLocation FROZEN_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ProjectShadow.MOD_ID, "boat/frozen"), "main");
    public static final ModelLayerLocation FROZEN_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ProjectShadow.MOD_ID, "chest_boat/frozen"), "main");
    public static final ModelLayerLocation DUNGEONICE_LAYER = new ModelLayerLocation(
            new ResourceLocation(ProjectShadow.MOD_ID, "dungeonice_layer"), "main");
}

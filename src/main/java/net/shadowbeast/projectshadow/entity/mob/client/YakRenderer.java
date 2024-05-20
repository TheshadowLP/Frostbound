package net.shadowbeast.projectshadow.entity.mob.client;

import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.camel.Camel;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.client.layers.ModModelLayers;
import net.shadowbeast.projectshadow.entity.mob.custom.DungeonIceEntity;
import net.shadowbeast.projectshadow.entity.mob.custom.YakEntity;
import org.jetbrains.annotations.NotNull;

public class YakRenderer extends MobRenderer<YakEntity, YakModel<YakEntity>> {
    private static final ResourceLocation CAMEL_LOCATION = new ResourceLocation("textures/entity/camel/camel.png");

    public YakRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new YakModel<>(pContext.bakeLayer(ModelLayers.CAMEL)), 0.7F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(YakEntity pEntity) {
        return CAMEL_LOCATION;
    }
}

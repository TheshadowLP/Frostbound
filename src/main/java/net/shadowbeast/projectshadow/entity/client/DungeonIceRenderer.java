package net.shadowbeast.projectshadow.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.client.layers.ModModelLayers;
import net.shadowbeast.projectshadow.entity.custom.DungeonIceEntity;

public class DungeonIceRenderer extends MobRenderer<DungeonIceEntity, DungeonIceModel<DungeonIceEntity>> {
    public DungeonIceRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DungeonIceModel<>(pContext.bakeLayer(ModModelLayers.DUNGEONICE_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(DungeonIceEntity pEntity) {
        return new ResourceLocation(ProjectShadow.MOD_ID, "textures/entity/dungeonice.png");
    }
}

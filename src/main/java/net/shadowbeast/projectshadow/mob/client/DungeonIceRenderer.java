package net.shadowbeast.projectshadow.mob.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.client.layers.ModModelLayers;
import net.shadowbeast.projectshadow.mob.DungeonIceEntity;
import org.jetbrains.annotations.NotNull;

public class DungeonIceRenderer extends MobRenderer<DungeonIceEntity, DungeonIceModel<DungeonIceEntity>> {
    public DungeonIceRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DungeonIceModel<>(pContext.bakeLayer(ModModelLayers.DUNGEONICE_LAYER)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DungeonIceEntity pEntity) {
        return new ResourceLocation(ProjectShadow.MOD_ID, "textures/entity/dungeonice.png");
    }
}

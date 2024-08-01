package net.shadowbeast.frostbound.entities.mobs.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.client.ModelLayers;
import net.shadowbeast.frostbound.entities.mobs.custom.DungeonIceEntity;
import org.jetbrains.annotations.NotNull;

public class DungeonIceRenderer extends MobRenderer<DungeonIceEntity, DungeonIceModel<DungeonIceEntity>> {
    public DungeonIceRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DungeonIceModel<>(pContext.bakeLayer(ModelLayers.DUNGEONICE_LAYER)), 1.2f);
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DungeonIceEntity pEntity) {
        return new ResourceLocation(Frostbound.MOD_ID, "textures/entity/dungeonice.png");
    }
}

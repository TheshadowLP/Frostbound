package net.shadowbeast.arcanemysteries.entities.mobs.client;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.entities.mobs.custom.YakEntity;
import org.jetbrains.annotations.NotNull;
public class YakRenderer extends MobRenderer<YakEntity, YakModel<YakEntity>> {
    private static final ResourceLocation CAMEL_LOCATION = new ResourceLocation("textures/entity/camel/camel.png");
    public YakRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new YakModel<>(pContext.bakeLayer(ModelLayers.CAMEL)), 0.7F);
    }
    /**
     * Returns the location of an entity's texture.
     */
    public @NotNull ResourceLocation getTextureLocation(@NotNull YakEntity pEntity) {
        return CAMEL_LOCATION;
    }
}

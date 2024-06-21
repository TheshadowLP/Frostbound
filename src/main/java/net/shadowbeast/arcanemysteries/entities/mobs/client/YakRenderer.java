package net.shadowbeast.arcanemysteries.entities.mobs.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.client.ModelLayers;
import net.shadowbeast.arcanemysteries.entities.mobs.custom.YakEntity;
import org.jetbrains.annotations.NotNull;
public class YakRenderer extends MobRenderer<YakEntity, YakModel<YakEntity>> {
    public YakRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new YakModel<>(pContext.bakeLayer(ModelLayers.YAK)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull YakEntity pEntity) {
        return new ResourceLocation(ArcaneMysteries.MOD_ID, "textures/entity/yak.png");
    }
}

package net.shadowbeast.frostbound.entities.mobs.client;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.frostbound.Frostbound;
import net.shadowbeast.frostbound.client.ModelLayers;
import net.shadowbeast.frostbound.entities.mobs.custom.YakEntity;
import net.shadowbeast.frostbound.entities.mobs.custom.variant.YakVariant;

import java.util.Map;

public class YakRenderer extends MobRenderer<YakEntity, YakModel<YakEntity>> {
    public static final Map<YakVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(YakVariant.class), (map) -> {
                map.put(YakVariant.BLACK,
                        new ResourceLocation(Frostbound.MOD_ID, "textures/entity/yak/black.png"));
                map.put(YakVariant.BROWN,
                        new ResourceLocation(Frostbound.MOD_ID, "textures/entity/yak/brown.png"));
                map.put(YakVariant.WHITE,
                        new ResourceLocation(Frostbound.MOD_ID, "textures/entity/yak/white.png"));
                map.put(YakVariant.GRAY,
                        new ResourceLocation(Frostbound.MOD_ID, "textures/entity/yak/gray.png"));
            });

    public YakRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new YakModel<>(pContext.bakeLayer(ModelLayers.YAK)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(YakEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }
}

package net.shadowbeast.arcanemysteries.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.entities.boats.EntityBoat;
import net.shadowbeast.arcanemysteries.entities.boats.EntityChestBoat;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;
public class BoatModRenderer extends BoatRenderer {
    private final Map<EntityBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;
    public BoatModRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(EntityBoat.Type.values()).collect(ImmutableMap.toImmutableMap((type) -> type,
                (type) -> Pair.of(new ResourceLocation(ArcaneMysteries.MODID, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat))));
    }
    private static String getTextureLocation(EntityBoat.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }
    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, EntityBoat.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? BoatModRenderer.createChestBoatModelName(pType) : BoatModRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }
    public static ModelLayerLocation createBoatModelName(EntityBoat.Type pType) {
        return createLocation("boat/" + pType.getName());
    }
    public static ModelLayerLocation createChestBoatModelName(EntityBoat.Type pType) {
        return createLocation("chest_boat/" + pType.getName());
    }
    private static ModelLayerLocation createLocation(String pPath) {
        return new ModelLayerLocation(new ResourceLocation(ArcaneMysteries.MODID, pPath), "main");
    }
    public @NotNull Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
        if(boat instanceof EntityBoat modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if(boat instanceof EntityChestBoat entityChestBoat) {
            return this.boatResources.get(entityChestBoat.getModVariant());
        } else {
            return null;
        }
    }
}
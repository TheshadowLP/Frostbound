package net.shadowbeast.arcanemysteries.json;

import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.arcanemysteries.interfaces.IResourceReloadListener;

public class EntityTemperatureDataManager implements IResourceReloadListener<Map<ResourceLocation, EntityTemperatureJsonHolder>> {
    @Override
    public CompletableFuture<Map<ResourceLocation, EntityTemperatureJsonHolder>> load(ResourceManager manager, ProfilerFiller profiler, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            Map<ResourceLocation, EntityTemperatureJsonHolder> drinkMap = new HashMap<>();

            for (Entry<ResourceLocation, Resource> resource : manager.listResources("survive_modifiers/entities", (s) -> s.toString().endsWith(".json")).entrySet()) {
                ResourceLocation entityId = new ResourceLocation(
                        resource.getKey().getNamespace(),
                        resource.getKey().getPath().replace("survive_modifiers/entities/", "").replace(".json", "")
                );

                if (ForgeRegistries.ENTITY_TYPES.containsKey(entityId)) {
                    try {
                        try (InputStream stream = resource.getValue().open();
                             InputStreamReader reader = new InputStreamReader(stream)) {

                            JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();
                            EntityTemperatureJsonHolder blockData = new EntityTemperatureJsonHolder(entityId, object);

                            drinkMap.put(entityId, blockData);
                        }
                    } catch (Exception e) {
                    }
                } else {
                }
            }

            return drinkMap;
        });
    }

    @Override
    public CompletableFuture<Void> apply(Map<ResourceLocation, EntityTemperatureJsonHolder> data, ResourceManager manager, ProfilerFiller profiler, Executor executor) {
        return CompletableFuture.runAsync(() -> {
            for (ResourceLocation drinkId : data.keySet()) {
                ArcaneMysteries.registerEntityTemperatures(drinkId, data.get(drinkId));
            }
        });
    }

    @Override
    public ResourceLocation id() {
        return new ResourceLocation(ArcaneMysteries.MOD_ID,"entity_data");
    }
}

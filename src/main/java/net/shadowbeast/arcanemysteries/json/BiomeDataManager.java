package net.shadowbeast.arcanemysteries.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.registries.ForgeRegistries;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.interfaces.IResourceReloadListener;
import net.shadowbeast.arcanemysteries.util.BiomeJsonHolder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BiomeDataManager implements IResourceReloadListener<Map<ResourceLocation, BiomeJsonHolder>> {
    @Override
    public CompletableFuture<Map<ResourceLocation, BiomeJsonHolder>> load(ResourceManager manager, ProfilerFiller profiler, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            Map<ResourceLocation, BiomeJsonHolder> drinkMap = new HashMap<>();

            for (Map.Entry<ResourceLocation, Resource> resource : manager.listResources("survive_modifiers/biomes", (s) -> s.toString().endsWith(".json")).entrySet()) {
                ResourceLocation blockId = new ResourceLocation(
                        resource.getKey().getNamespace(),
                        resource.getKey().getPath().replace("survive_modifiers/biomes/", "").replace(".json", "")
                );

                if (!ForgeRegistries.BIOMES.containsKey(blockId)) {
                }
                try {
                    try (InputStream stream = resource.getValue().open();
                         InputStreamReader reader = new InputStreamReader(stream)) {

                        JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();
                        BiomeJsonHolder biomeData = new BiomeJsonHolder(blockId, object);
                        drinkMap.put(blockId, biomeData);
                    }
                } catch (Exception e) {
                }
            }

            return drinkMap;
        });
    }

    @Override
    public CompletableFuture<Void> apply(Map<ResourceLocation, BiomeJsonHolder> data, ResourceManager manager, ProfilerFiller profiler, Executor executor) {
        return CompletableFuture.runAsync(() -> {
            for (ResourceLocation drinkId : data.keySet()) {
                ArcaneMysteries.registerBiomeTemperatures(drinkId, data.get(drinkId));
            }
        });
    }

    @Override
    public ResourceLocation id() {
        return new ResourceLocation(ArcaneMysteries.MOD_ID,"biome_data");
    }
}
package net.shadowbeast.arcanemysteries.interfaces;

import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface IResourceReloadListener<T> extends ReloadListener {

    @Override
    default CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        Objects.requireNonNull(stage);
        return load(resourceManager, preparationsProfiler, backgroundExecutor)
                .thenCompose(stage::wait)
                .thenAccept(value -> apply(value, resourceManager, reloadProfiler, gameExecutor));
    }

    CompletableFuture<T> load(ResourceManager resourceManager, ProfilerFiller preparationsProfiler, Executor backgroundExecutor);

    CompletableFuture<Void> apply(T data, ResourceManager resourceManager, ProfilerFiller reloadProfiler, Executor gameExecutor);
}

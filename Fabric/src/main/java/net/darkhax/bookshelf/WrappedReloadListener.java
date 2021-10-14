package net.darkhax.bookshelf;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Fabric requests modded reload listeners to implement their IdentifiableResourceReloadListener
 * interface. This implementation simply wraps a standard vanilla compliant implementation to make
 * it Fabric compliant.
 */
public class WrappedReloadListener implements IdentifiableResourceReloadListener {

    /**
     * The ID of the reload listener. Fabric requires this to identify listeners by ID.
     */
    private final ResourceLocation id;

    /**
     * The internal wrapped reload listener.
     */
    private final PreparableReloadListener reloadListener;

    public WrappedReloadListener(ResourceLocation id, PreparableReloadListener reloadListener) {

        this.id = id;
        this.reloadListener = reloadListener;
    }

    @Override
    public ResourceLocation getFabricId() {

        return this.id;
    }

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {

        return reloadListener.reload(preparationBarrier, resourceManager, profilerFiller, profilerFiller2, executor, executor2);
    }
}

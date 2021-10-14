package net.darkhax.bookshelf;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class TestListener extends SimpleJsonResourceReloadListener {

    public TestListener(String folder) {

        super(new GsonBuilder().create(), folder);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> dataEntries, ResourceManager resources, ProfilerFiller profiler) {

        dataEntries.forEach((k, v) -> Bookshelf.LOG.info("{} --- {}", k, v.toString()));
    }
}

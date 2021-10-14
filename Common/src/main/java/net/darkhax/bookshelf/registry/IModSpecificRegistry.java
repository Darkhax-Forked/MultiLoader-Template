package net.darkhax.bookshelf.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.BiConsumer;

public interface IModSpecificRegistry<T> {

    default <VT extends T> VT add(VT value, String id) {

        return this.add(value, new ResourceLocation(this.getOwnerId(), id));
    }

    <VT extends T> VT add(VT value, ResourceLocation id);

    Map<ResourceLocation, T> getEntries();

    String getOwnerId();

    void addInsertListener(BiConsumer<ResourceLocation, T> listener);

    boolean isEmpty();
}
package net.darkhax.bookshelf.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModSpecificRegistry<T> implements IModSpecificRegistry<T> {

    private final String ownerId;
    private final Map<ResourceLocation, T> values;
    private final Map<ResourceLocation, T> valuesUnmodifiable;
    private final List<BiConsumer<ResourceLocation, T>> insertListeners;

    public ModSpecificRegistry(String ownerId) {

        this.ownerId = ownerId;
        this.values = new LinkedHashMap<>();
        this.valuesUnmodifiable = Collections.unmodifiableMap(this.values);
        this.insertListeners = new ArrayList<>();
    }

    @Override
    public <VT extends T> VT add(VT value, ResourceLocation id) {

        // Check for a duplicate value. If so fail fatally.
        if (this.values.containsKey(id)) {

            throw new IllegalArgumentException("The ID " + id.toString() + " has already been registered!");
        }

        this.values.put(id, value);
        this.insertListeners.forEach(listener -> listener.accept(id, value));
        return value;
    }

    @Override
    public Map<ResourceLocation, T> getEntries() {

        return this.valuesUnmodifiable;
    }

    @Override
    public String getOwnerId() {

        return this.ownerId;
    }

    @Override
    public void addInsertListener(BiConsumer<ResourceLocation, T> listener) {

        this.insertListeners.add(listener);
    }

    @Override
    public boolean isEmpty() {

        return this.values.isEmpty();
    }
}
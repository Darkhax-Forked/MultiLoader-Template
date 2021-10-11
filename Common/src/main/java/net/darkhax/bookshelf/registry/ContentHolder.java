package net.darkhax.bookshelf.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.resources.ResourceLocation;

/**
 * A default implementation of IContentHolder. Values are held in a plain Java HashMap.
 *
 * @param <T> The type of value held by the holder.
 */
public class ContentHolder<T> implements IContentHolder<T> {
    
    /**
     * The ID of the mod that owns this holder.
     */
    private final String ownerId;
    
    /**
     * A map of all held content.
     */
    private final Map<ResourceLocation, T> values;
    
    /**
     * An unmodifiable view of the values map.
     */
    private final Map<ResourceLocation, T> valuesUnmodifiable;
    
    /**
     * A list of all insert listeners. These are notified when a value is added to the holder.
     */
    private final List<BiConsumer<ResourceLocation, T>> insertListeners;
    
    public ContentHolder(String ownerId) {
        
        this.ownerId = ownerId;
        this.values = new LinkedHashMap<>();
        this.valuesUnmodifiable = Collections.unmodifiableMap(this.values);
        this.insertListeners = new ArrayList<>();
    }
    
    @Override
    public <VT extends T> VT add(VT value, ResourceLocation id) {
        
        // Check for a duplicate value. If so fail fatally.
        if(this.values.containsKey(id)) {
            
            throw new IllegalArgumentException("The ID " + id.toString() + " has already been registered!");
        }
        
        this.values.put(id, value);
        this.insertListeners.forEach(listener -> listener.accept(id, value));
        return value;
    }
    
    @Override
    public Map<ResourceLocation, T> getValues() {
        
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
}
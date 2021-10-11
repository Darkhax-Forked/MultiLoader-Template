package net.darkhax.bookshelf.registry;

import net.darkhax.bookshelf.config.RegistryEntryConfig;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * A content holder will hold registrable content from a specific mod. Entries are held here to allow quick lookup and
 * debugging in the future. The actual registration of the held content is delegated to external loader specific code.
 *
 * @param <T> The type of content held in the holder.
 */
public interface IContentHolder<T> {

    /**
     * Adds an individual value to the holder. The registration of this content is delegated to external loader specific
     * code and will not happen immediately.
     *
     * @param value The value to add to the holder.
     * @param id    The ID to assign the value when registered. Only accepts the path or second half of the ID. The
     *              namespace will be taken from getOwnerId.
     * @param <VT>  The type of the value being added.
     * @return The value being added.
     */
    default <VT extends T> VT add(VT value, String id) {

        return this.add(value, new ResourceLocation(this.getOwnerId(), id));
    }

    default <VT extends T> VT add(RegistryEntryConfig<VT> builder) {

        builder.updateOwner(this.getOwnerId());
        return add(builder.get(), builder.getEntryId());
    }

    /**
     * Adds an individual value to the holder. The registration of this content is delegated to external loader specific
     * code and will not happen immediately.
     *
     * @param value The value to add to the holder.
     * @param id    The ID to assign the value when registered.
     * @param <VT>  The type of the value being added.
     * @return The value being added.
     */
    <VT extends T> VT add(VT value, ResourceLocation id);

    /**
     * Gets an unmodifiable map containing all held values and their associated IDs.
     *
     * @return An unmodifiable map of all held values.
     */
    Map<ResourceLocation, T> getValues();

    /**
     * Gets the ID of the mod that owns this holder and all the contained entries.
     *
     * @return The ID of the mod that owns this holder.
     */
    String getOwnerId();

    /**
     * Registers all held entries into an external registry.
     *
     * @param registry A consumer function that handles the registration of the held values.
     */
    default void registerEntries(BiConsumer<ResourceLocation, T> registry) {

        this.getValues().forEach(registry);
    }

    /**
     * Adds a listener that will be notified every time a value is added to the holder. This can be used to apply broad
     * changes to added content such as applying a creative tab to all registered items. This can also be used to
     * automatically generate new content in different content holders such as generating spawn eggs for entities.
     *
     * @param listener The listener to notify.
     */
    void addInsertListener(BiConsumer<ResourceLocation, T> listener);
}
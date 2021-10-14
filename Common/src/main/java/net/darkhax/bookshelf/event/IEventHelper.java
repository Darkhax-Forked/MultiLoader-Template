package net.darkhax.bookshelf.event;

import net.minecraft.server.ServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * An event helper provides an interface for registering simplified event hooks with the loader specific event system.
 * This system is more of a convenience than a necessity and can be expanded by implementing mods to add new events or
 * handle things with different priority levels.
 */
public interface IEventHelper {

    /**
     * Notifies an event listener every time a tooltip is rendered for an ItemStack.
     *
     * @param event The event listener.
     */
    void onItemTooltip(IItemTooltipEvent event);

    /**
     * Notifies an event listener every time the attribute modifiers for an ItemStack are calculated.
     *
     * @param event The event listener.
     */
    void onItemAttributeModifiers(IItemAttributeModifierEvent event);

    /**
     * Notifies an event listener when the server is collecting it's reload listeners. This applies to both physical/dedicated and logical/integrated servers.
     *
     * @param event The event listener.
     */
    void addServerReloadListeners(BiConsumer<ServerResources, Consumer<PreparableReloadListener>> event);

    void addClientReloadListener(Consumer<PreparableReloadListener> event);
}

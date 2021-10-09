package net.darkhax.bookshelf.event;

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
    public void onItemTooltip(IItemTooltipEvent event);
    
    /**
     * Notifies an event listener every time the attribute modifiers for an ItemStack are calculated.
     *
     * @param event The event listener.
     */
    public void onItemAttributeModifiers(IItemAttributeModifierEvent event);
    
}

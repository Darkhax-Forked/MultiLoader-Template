package net.darkhax.bookshelf.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

/**
 * Holds new events related to items and item stacks.
 */
public class ItemEvents {
    
    /**
     * This event is sent out every time the attribute modifiers for an ItemStack are calculated.
     */
    public static final Event<IItemAttributeModifierEvent> ITEM_MODIFIER = EventFactory.createArrayBacked(IItemAttributeModifierEvent.class, callbacks -> (stack, slot, modifiers) -> {
        for(IItemAttributeModifierEvent callback : callbacks) {
            callback.calculateModifiers(stack, slot, modifiers);
        }
    });
    
}

package net.darkhax.bookshelf.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class EventHelperFabric implements IEventHelper {
    
    @Override
    public void onItemTooltip(IItemTooltipEvent event) {
        
        ItemTooltipCallback.EVENT.register((s, f, l) -> event.onNotify(s, l, f));
    }
    
    @Override
    public void onItemAttributeModifiers(IItemAttributeModifierEvent event) {
        
        ItemEvents.ITEM_MODIFIER.register(event);
    }
    
}
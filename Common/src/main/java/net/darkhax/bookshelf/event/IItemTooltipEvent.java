package net.darkhax.bookshelf.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * An event listener that will be notified every time the tooltips for an ItemStack are being generated. These listeners
 * have the ability to modify the tooltip as needed.
 */
public interface IItemTooltipEvent {
    
    /**
     * Notifies the event listener that the tooltips for an ItemStack are being generated.
     *
     * @param stack   The ItemStack being looked at.
     * @param tooltip The list of tooltips to display.
     * @param flag    User controlled options for what types of information tooltips should display.
     */
    void onNotify(ItemStack stack, List<Component> tooltip, TooltipFlag flag);
    
}
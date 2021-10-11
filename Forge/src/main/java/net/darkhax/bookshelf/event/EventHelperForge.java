package net.darkhax.bookshelf.event;

import net.darkhax.bookshelf.util.ForgeHacks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class EventHelperForge implements IEventHelper {

    @Override
    public void onItemTooltip(IItemTooltipEvent event) {

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, e -> event.onNotify(e.getItemStack(), e.getToolTip(), e.getFlags()));
    }

    @Override
    public void onItemAttributeModifiers(IItemAttributeModifierEvent event) {

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ItemAttributeModifierEvent.class, e -> event.calculateModifiers(e.getItemStack(), e.getSlotType(), () -> ForgeHacks.getMutableModifiers(e)));
    }
}
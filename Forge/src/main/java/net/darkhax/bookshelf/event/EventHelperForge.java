package net.darkhax.bookshelf.event;

import net.darkhax.bookshelf.util.ForgeHacks;
import net.minecraft.server.ServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EventHelperForge implements IEventHelper {

    @Override
    public void onItemTooltip(IItemTooltipEvent event) {

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ItemTooltipEvent.class, e -> event.onNotify(e.getItemStack(), e.getToolTip(), e.getFlags()));
    }

    @Override
    public void onItemAttributeModifiers(IItemAttributeModifierEvent event) {

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ItemAttributeModifierEvent.class, e -> event.calculateModifiers(e.getItemStack(), e.getSlotType(), () -> ForgeHacks.getMutableModifiers(e)));
    }

    @Override
    public void addServerReloadListeners(BiConsumer<ServerResources, Consumer<PreparableReloadListener>> event) {

    }

    @Override
    public void addClientReloadListener(Consumer<PreparableReloadListener> event) {

    }
}
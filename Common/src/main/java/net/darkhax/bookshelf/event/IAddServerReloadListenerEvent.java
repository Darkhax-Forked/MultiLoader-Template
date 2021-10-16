package net.darkhax.bookshelf.event;

import net.minecraft.server.ServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.function.Consumer;

/**
 * An event that is fired on logical and physical servers when they are collecting their reload listeners. This event
 * allows you to register your own reload listeners.
 */
public interface IAddServerReloadListenerEvent {

    void onNotify(ServerResources resources, Consumer<PreparableReloadListener> register);
}

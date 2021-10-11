package net.darkhax.bookshelf;

import net.darkhax.bookshelf.event.IEventHelper;
import net.darkhax.bookshelf.util.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {
    
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IEventHelper EVENTS = load(IEventHelper.class);
    
    public static <T> T load(Class<T> clazz) {
        
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Bookshelf.LOG.debug("Loaded {} for service {}.", loadedService, clazz);
        return loadedService;
    }
    
}
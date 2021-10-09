package net.darkhax.bookshelf;

import net.darkhax.bookshelf.event.EventHelperFabric;
import net.darkhax.bookshelf.registry.RegistryHelperFabric;
import net.fabricmc.api.ModInitializer;

public class BookshelfFabric implements ModInitializer {
    
    private final EventHelperFabric eventHelper = new EventHelperFabric();
    private Bookshelf modInstance;
    private RegistryHelperFabric registryHelper;
    
    @Override
    public void onInitialize() {
        
        this.modInstance = new Bookshelf(eventHelper);
        this.registryHelper = new RegistryHelperFabric();
        
        this.registryHelper.registerContent(this.modInstance.content);
    }
    
}
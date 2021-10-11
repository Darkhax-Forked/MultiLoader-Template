package net.darkhax.bookshelf;

import net.darkhax.bookshelf.registry.RegistryHelperFabric;
import net.fabricmc.api.ModInitializer;

public class BookshelfFabric implements ModInitializer {
    
    private Bookshelf modInstance;
    private RegistryHelperFabric registryHelper;
    
    @Override
    public void onInitialize() {
        
        this.modInstance = new Bookshelf();
        this.registryHelper = new RegistryHelperFabric();
        this.registryHelper.registerContent(this.modInstance.content);
    }
}
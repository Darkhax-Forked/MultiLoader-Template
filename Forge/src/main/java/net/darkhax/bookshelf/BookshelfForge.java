package net.darkhax.bookshelf;

import net.darkhax.bookshelf.event.EventHelperForge;
import net.darkhax.bookshelf.registry.RegistryHelperForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Bookshelf.MOD_ID)
public class BookshelfForge {
    
    private final EventHelperForge eventHelper = new EventHelperForge();
    private final Bookshelf modInstance;
    private final RegistryHelperForge registryHelper;
    
    public BookshelfForge() {
        
        this.modInstance = new Bookshelf(eventHelper);
        this.registryHelper = new RegistryHelperForge();
        
        this.registryHelper.registerContent(this.modInstance.content);
    }
    
}
package net.darkhax.bookshelf;

import net.darkhax.bookshelf.registry.RegistryHelperForge;
import net.minecraftforge.common.asm.RuntimeEnumExtender;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod(Bookshelf.MOD_ID)
public class BookshelfForge {
    
    private final Bookshelf modInstance;
    private final RegistryHelperForge registryHelper;
    
    public BookshelfForge() {
        
        this.modInstance = new Bookshelf();
        this.registryHelper = new RegistryHelperForge();
        this.registryHelper.registerContent(this.modInstance.content);
    }
}
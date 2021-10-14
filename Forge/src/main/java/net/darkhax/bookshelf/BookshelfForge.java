package net.darkhax.bookshelf;

import net.minecraftforge.fml.common.Mod;

@Mod(Bookshelf.MOD_ID)
public class BookshelfForge {

    private final Bookshelf modInstance;

    public BookshelfForge() {

        this.modInstance = new Bookshelf();
    }
}
package net.darkhax.bookshelf;

import net.fabricmc.api.ModInitializer;

public class BookshelfFabric implements ModInitializer {

    private Bookshelf modInstance;

    @Override
    public void onInitialize() {

        this.modInstance = new Bookshelf();
    }
}
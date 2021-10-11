package net.darkhax.bookshelf.registry;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * A ContentManager is responsible for holding all of the common supported content holders. Mods should register their
 * content into a content manager and then allow a loader specific IRegistryHelper to load and register the content when
 * appropriate.
 */
public class ContentManager {

    public final IContentHolder<Block> blocks;
    public final IContentHolder<Item> items;
    public final IContentHolder<Motive> paintings;

    public ContentManager(String ownerId) {

        this.blocks = new ContentHolder<>(ownerId);
        this.items = new ContentHolder<>(ownerId);
        this.paintings = new ContentHolder<>(ownerId);
    }
}
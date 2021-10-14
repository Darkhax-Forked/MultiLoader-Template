package net.darkhax.bookshelf;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class Content {

    public final Block testBlock;
    public final Item testItem;
    public final Motive testPainting;

    public Content(RegistryHelper registry) {

        this.testBlock = registry.blocks.add(new Block(BlockBehaviour.Properties.of(Material.CLAY)), "test_block");
        this.testItem = registry.items.add(new Item(new Item.Properties()), "test_item");
        this.testPainting = registry.paintings.add(new Motive(32, 16), "test_painting");

        registry.clientReloadListeners.add(new TestListener("test_one"), "test_one");
        registry.serverReloadListeners.add(new TestListener("test_two"), "test_two");
    }
}
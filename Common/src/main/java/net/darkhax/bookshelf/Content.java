package net.darkhax.bookshelf;

import net.darkhax.bookshelf.registry.ContentManager;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class Content extends ContentManager {

    public final Block testBlock;
    public final Item testItem;
    public final Motive testPainting;

    public Content(String ownerId) {

        super(ownerId);

        this.testBlock = this.blocks.add(new Block(BlockBehaviour.Properties.of(Material.CLAY)), "test_block");
        this.testItem = this.items.add(new Item(new Item.Properties()), "test_item");
        this.testPainting = this.paintings.add(new Motive(32, 16), "test_painting");
    }
}
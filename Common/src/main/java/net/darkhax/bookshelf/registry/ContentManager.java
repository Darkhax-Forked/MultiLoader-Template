package net.darkhax.bookshelf.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

/**
 * A ContentManager is responsible for holding all of the common supported content holders. Mods should register their
 * content into a content manager and then allow a loader specific IRegistryHelper to load and register the content when
 * appropriate.
 */
public class ContentManager {

    public final IContentHolder<Block> blocks;
    public final IContentHolder<Item> items;
    public final IContentHolder<Enchantment> enchantments;
    public final IContentHolder<Motive> paintings;
    public final IContentHolder<MobEffect> mobEffects;
    public final IContentHolder<Attribute> attributes;
    public final IContentHolder<VillagerProfession> villagerProfessions;

    public ContentManager(String ownerId) {

        this.blocks = new ContentHolder<>(ownerId);
        this.items = new ContentHolder<>(ownerId);
        this.enchantments = new ContentHolder<>(ownerId);
        this.paintings = new ContentHolder<>(ownerId);
        this.mobEffects = new ContentHolder<>(ownerId);
        this.attributes = new ContentHolder<>(ownerId);
        this.villagerProfessions = new ContentHolder<>(ownerId);
    }
}
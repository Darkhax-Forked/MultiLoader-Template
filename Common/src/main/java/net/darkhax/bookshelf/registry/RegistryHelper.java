package net.darkhax.bookshelf.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public abstract class RegistryHelper {

    public final IModSpecificRegistry<Block> blocks;
    public final IModSpecificRegistry<Item> items;
    public final IModSpecificRegistry<Enchantment> enchantments;
    public final IModSpecificRegistry<Motive> paintings;
    public final IModSpecificRegistry<MobEffect> mobEffects;
    public final IModSpecificRegistry<Attribute> attributes;
    public final IModSpecificRegistry<VillagerProfession> villagerProfessions;

    protected RegistryHelper(String ownerId) {

        this.blocks = new ModSpecificRegistry<>(ownerId);
        this.items = new ModSpecificRegistry<>(ownerId);
        this.enchantments = new ModSpecificRegistry<>(ownerId);
        this.paintings = new ModSpecificRegistry<>(ownerId);
        this.mobEffects = new ModSpecificRegistry<>(ownerId);
        this.attributes = new ModSpecificRegistry<>(ownerId);
        this.villagerProfessions = new ModSpecificRegistry<>(ownerId);
    }

    public abstract void init();
}
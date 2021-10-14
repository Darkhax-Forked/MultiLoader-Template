package net.darkhax.bookshelf.registry;

import net.minecraft.core.Registry;

public class RegistryHelperFabric extends RegistryHelper {

    public RegistryHelperFabric(String ownerId) {

        super(ownerId);
    }

    @Override
    public void init() {

        this.consumeVanillaRegistry(blocks, Registry.BLOCK);
        this.consumeVanillaRegistry(items, Registry.ITEM);
        this.consumeVanillaRegistry(enchantments, Registry.ENCHANTMENT);
        this.consumeVanillaRegistry(paintings, Registry.MOTIVE);
        this.consumeVanillaRegistry(mobEffects, Registry.MOB_EFFECT);
        this.consumeVanillaRegistry(attributes, Registry.ATTRIBUTE);
        this.consumeVanillaRegistry(villagerProfessions, Registry.VILLAGER_PROFESSION);
    }

    private <T> void consumeVanillaRegistry(IModSpecificRegistry<T> toRegister, Registry<T> registry) {

        toRegister.getValues().forEach((id, value) -> Registry.register(registry, id, value));
    }
}
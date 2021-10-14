package net.darkhax.bookshelf.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class RegistryHelperFabric implements IRegistryHelper {

    @Override
    public void registerContent(ContentManager content) {

        content.blocks.registerEntries(vanillaRegistryHandler(Registry.BLOCK));
        content.items.registerEntries(vanillaRegistryHandler(Registry.ITEM));
        content.enchantments.registerEntries(vanillaRegistryHandler(Registry.ENCHANTMENT));
        content.paintings.registerEntries(vanillaRegistryHandler(Registry.MOTIVE));
        content.mobEffects.registerEntries(vanillaRegistryHandler(Registry.MOB_EFFECT));
        content.attributes.registerEntries(vanillaRegistryHandler(Registry.ATTRIBUTE));
        content.villagerProfessions.registerEntries(vanillaRegistryHandler(Registry.VILLAGER_PROFESSION));
    }

    private <T> BiConsumer<ResourceLocation, T> vanillaRegistryHandler(Registry<T> registry) {

        return (id, value) -> Registry.register(registry, id, value);
    }
}
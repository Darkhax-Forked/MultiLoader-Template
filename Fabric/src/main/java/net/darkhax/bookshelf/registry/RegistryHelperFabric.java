package net.darkhax.bookshelf.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class RegistryHelperFabric implements IRegistryHelper {

    @Override
    public void registerContent(ContentManager content) {

        content.blocks.registerEntries(vanillaRegistryHandler(Registry.BLOCK));
        content.items.registerEntries(vanillaRegistryHandler(Registry.ITEM));
        content.paintings.registerEntries(vanillaRegistryHandler(Registry.MOTIVE));
    }

    private <T> BiConsumer<ResourceLocation, T> vanillaRegistryHandler(Registry<T> registry) {

        return (id, value) -> Registry.register(registry, id, value);
    }
}
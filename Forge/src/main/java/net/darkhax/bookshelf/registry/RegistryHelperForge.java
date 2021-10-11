package net.darkhax.bookshelf.registry;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Consumer;

public class RegistryHelperForge implements IRegistryHelper {

    @Override
    public void registerContent(ContentManager content) {

        createRegistryListener(Block.class, content.blocks);
        createRegistryListener(Item.class, content.items);
        createRegistryListener(Motive.class, content.paintings);
    }

    private <T extends IForgeRegistryEntry<T>> void createRegistryListener(Class<T> clazz, IContentHolder<T> holder) {

        final Consumer<RegistryEvent.Register<T>> listener = event -> holder.registerEntries((id, value) -> {

            if (value.getRegistryName() == null) {
                value.setRegistryName(id);
            }

            event.getRegistry().register(value);
        });

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(clazz, listener);
    }
}

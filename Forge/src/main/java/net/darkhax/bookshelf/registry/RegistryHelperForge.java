package net.darkhax.bookshelf.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Consumer;

public class RegistryHelperForge extends RegistryHelper {

    public RegistryHelperForge(String ownerId) {

        super(ownerId);
    }

    @Override
    public void init() {

        consumeRegistry(Block.class, this.blocks);
        consumeRegistry(Item.class, this.items);
        consumeRegistry(Enchantment.class, this.enchantments);
        consumeRegistry(Motive.class, this.paintings);
        consumeRegistry(MobEffect.class, this.mobEffects);
        consumeRegistry(Attribute.class, this.attributes);
        consumeRegistry(VillagerProfession.class, this.villagerProfessions);
    }

    private <T extends IForgeRegistryEntry<T>> void consumeRegistry(Class<T> clazz, IModSpecificRegistry<T> registry) {

        if (!registry.isEmpty()) {

            final Consumer<RegistryEvent.Register<T>> listener = event -> {

                registry.getValues().forEach((id, value) -> {

                    if (value.getRegistryName() == null) {

                        value.setRegistryName(id);
                    }

                    event.getRegistry().register(value);
                });
            };

            FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(clazz, listener);
        }
    }
}

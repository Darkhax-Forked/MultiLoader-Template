package net.darkhax.bookshelf.registry;

public class RegistryFactoryFabric implements IRegistryFactory {

    @Override
    public RegistryHelper create(String modid) {

        return new RegistryHelperFabric(modid);
    }
}

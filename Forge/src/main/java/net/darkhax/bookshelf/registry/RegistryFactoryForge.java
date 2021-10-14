package net.darkhax.bookshelf.registry;

public class RegistryFactoryForge implements IRegistryFactory {

    @Override
    public RegistryHelper create(String modid) {

        return new RegistryHelperForge(modid);
    }
}
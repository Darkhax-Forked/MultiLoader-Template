package net.darkhax.bookshelf.registry;

/**
 * Defines a class that can register content from a content manager using loader specific code.
 */
public interface IRegistryHelper {

    /**
     * Registers content from the content manager using loader specific code.
     *
     * @param content The content to load.
     */
    public void registerContent(ContentManager content);
}

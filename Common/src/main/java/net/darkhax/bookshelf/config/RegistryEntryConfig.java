package net.darkhax.bookshelf.config;

import java.nio.file.Path;

public abstract class RegistryEntryConfig<T> extends ConfigBuilder<T> {
    
    private final String registryType;
    private final String entryId;
    private String ownerId;
    
    protected RegistryEntryConfig(String registryType, String entryId) {
        
        this.registryType = registryType;
        this.entryId = entryId;
    }
    
    public void updateOwner(String ownerId) {
        
        this.ownerId = ownerId;
    }
    
    public String getEntryId() {
        
        return this.entryId;
    }
    
    @Override
    public Path getPath() {
        
        return Path.of(this.ownerId + "/" + this.registryType + "/" + this.entryId + ".json");
    }
    
}

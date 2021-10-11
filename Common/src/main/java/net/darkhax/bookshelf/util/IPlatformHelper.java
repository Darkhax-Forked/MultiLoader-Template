package net.darkhax.bookshelf.util;

import net.darkhax.bookshelf.lib.PhysicalSide;

import java.io.File;
import java.nio.file.Path;

public interface IPlatformHelper {
    
    Path getGamePath();
    
    default File getGameDirectory() {
        
        return this.getGamePath().toFile();
    }
    
    Path getConfigPath();
    
    default File getConfigDirectory() {
        
        return this.getConfigPath().toFile();
    }
    
    default boolean createDirectory(File file) {
        
        if(!file.exists()) {
            
            return file.mkdirs();
        }
        
        return false;
    }
    
    boolean isModLoaded(String modId);
    
    boolean isDevelopmentEnvironment();
    
    PhysicalSide getPhysicalSide();
    
}
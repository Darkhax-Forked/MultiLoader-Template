package net.darkhax.bookshelf.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.darkhax.bookshelf.Services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

public abstract class ConfigBuilder <T> implements Supplier<T> {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Override
    public T get() {
    
        Path fullPath = Services.PLATFORM.getConfigPath().resolve(this.getPath());
        File file = fullPath.toFile();
        
        if (file.exists()) {
            
            try (FileReader reader = new FileReader(file)) {
            
                this.read(GSON.fromJson(reader, JsonObject.class));
            }
            
            catch(IOException e) {
                
                throw new RuntimeException(e);
            }
        }
        
        else {
        
            file.getParentFile().mkdirs();
            
            try (FileWriter writer = new FileWriter(file)) {
            
                GSON.toJson(write(new JsonObject()), writer);
            }
            
            catch(IOException e) {

                throw new RuntimeException(e);
            }
        }
    
        return this.create();
    }
    
    abstract void read(JsonObject json);
    
    abstract JsonObject write(JsonObject json);
    
    abstract T create();
    
    abstract Path getPath();
}
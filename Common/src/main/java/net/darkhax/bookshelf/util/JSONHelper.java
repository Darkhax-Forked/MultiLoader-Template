package net.darkhax.bookshelf.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class JSONHelper {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    public static JsonElement getAsElement(String jsonString) {
        
        return GSON.fromJson(jsonString, JsonElement.class);
    }
    
}
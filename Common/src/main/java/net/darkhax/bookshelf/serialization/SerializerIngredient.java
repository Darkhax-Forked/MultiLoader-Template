package net.darkhax.bookshelf.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.Registry;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.SerializationTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;

public class SerializerIngredient implements ISerializer<Ingredient> {
    
    public static final ISerializer<Ingredient> SERIALIZER = new SerializerIngredient();
    
    private SerializerIngredient() {
    
    }
    
    @Override
    public Ingredient fromJSON(JsonElement json) {
        
        return Ingredient.fromJson(json);
    }
    
    @Override
    public JsonElement toJSON(Ingredient toWrite) {
        
        return toWrite.toJson();
    }
    
    @Override
    public Ingredient fromByteBuf(FriendlyByteBuf buffer) {
        
        return Ingredient.fromNetwork(buffer);
    }
    
    @Override
    public void toByteBuf(FriendlyByteBuf buffer, Ingredient toWrite) {
        
        toWrite.toNetwork(buffer);
    }
    
    @Override
    public Tag toNBT(Ingredient toWrite) {
        
        return Serializers.STRING.toNBT(this.toJSONString(toWrite));
    }
    
    @Override
    public Ingredient fromNBT(Tag nbt) {
        
        return this.fromJSONString(Serializers.STRING.fromNBT(nbt));
    }
    
}
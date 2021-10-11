package net.darkhax.bookshelf.serialization;

import com.google.gson.JsonElement;

import net.minecraft.nbt.Tag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;

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
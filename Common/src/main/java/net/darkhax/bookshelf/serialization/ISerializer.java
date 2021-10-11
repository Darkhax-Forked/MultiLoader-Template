package net.darkhax.bookshelf.serialization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonParseException;
import net.darkhax.bookshelf.util.JSONHelper;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Provides a structure for serializing common data types into the various data exchange formats used in the game. These
 * currently support JSON, NBT, and byte buffers (networking).
 *
 * @param <T> The data type that is handled by the serializer.
 */
public interface ISerializer<T> {
    
    /**
     * Reads the value from a specific JSON element.
     *
     * @param json The JSON element to read data from.
     *
     * @return The value that was read.
     */
    T fromJSON(JsonElement json);
    
    /**
     * Reads a value from a member on a parent JSON object.
     *
     * @param json       The parent JSON object to read from.
     * @param memberName The name of the child element to read from.
     *
     * @return The value that was read.
     */
    default T fromJSON(JsonObject json, String memberName) {
        
        return this.fromJSON(json.get(memberName));
    }
    
    /**
     * Reads a value from a member on a parent JSON object.
     *
     * @param json       The parent JSON object to read from.
     * @param memberName The name of the child element to read from.
     * @param fallback   A fallback value used when the child element does not exist.
     *
     * @return The value that was read or the fallback value.
     */
    default T fromJSON(JsonObject json, String memberName, T fallback) {
        
        return json.has(memberName) ? this.fromJSON(json, memberName) : fallback;
    }
    
    /**
     * Reads a value from a member on a parent JSON object.
     *
     * @param json       The parent JSON object to read from.
     * @param memberName The name of the child element to read from.
     * @param fallback   A fallback value used when the child element does not exist.
     *
     * @return The value that was read or the fallback value.
     */
    default T fromJSON(JsonObject json, String memberName, Supplier<T> fallback) {
        
        return json.has(memberName) ? this.fromJSON(json, memberName) : fallback.get();
    }
    
    /**
     * Writes a value to a JSON element.
     *
     * @param toWrite The object to serialize into JSON data.
     *
     * @return The serialized JSON element.
     */
    JsonElement toJSON(T toWrite);
    
    /**
     * Reads a value from a byte buffer. This is commonly used for networking.
     *
     * @param buffer The byte buffer to read from.
     *
     * @return The value that was read.
     */
    T fromByteBuf(FriendlyByteBuf buffer);
    
    /**
     * Writes a value to a byte buffer. This is commonly used for networking.
     *
     * @param buffer  The byte buffer to write to.
     * @param toWrite The value to write.
     */
    void toByteBuf(FriendlyByteBuf buffer, T toWrite);
    
    /**
     * Writes a value to a named binary tag (NBT).
     *
     * @param toWrite The value to write.
     *
     * @return The serialized NBT data.
     */
    Tag toNBT(T toWrite);
    
    /**
     * Reads a value from a named binary tag (NBT).
     *
     * @param nbt The tag to read data from.
     *
     * @return The value that was read from the data.
     */
    T fromNBT(Tag nbt);
    
    /**
     * Reads a list of values from JSON. When given a JSON array each entry is added to the list. When given a JSON
     * object it will be treated as a list of one element.
     *
     * @param json The JSON element to read from.
     *
     * @return A list of values read from the JSON.
     */
    default List<T> fromJSONList(JsonElement json) {
        
        final List<T> list = new ArrayList<>();
        
        if(json.isJsonArray()) {
            
            for(final JsonElement element : json.getAsJsonArray()) {
                
                list.add(this.fromJSON(element));
            }
        } else {
            list.add(this.fromJSON(json));
        }
        
        return list;
    }
    
    /**
     * Reads a list of values from a child element on a JSON object. If the child element is an array each entry will be
     * added to the list. If the child element is an object it will be treated as a list of one.
     *
     * @param json       The parent JSON element.
     * @param memberName The member of the element to read from.
     *
     * @return The list of values read from the JSON.
     */
    default List<T> fromJSONList(JsonObject json, String memberName) {
        
        if(json.has(memberName)) {
            
            return this.fromJSONList(json.get(memberName));
        }
        
        throw new JsonParseException("Expected member " + memberName + " was not found.");
    }
    
    /**
     * Reads a list of values from a child element on a JSON object. If the child element is an array each entry will be
     * added to the list. If the child element is an object it will be treated as a list of one.
     *
     * @param json       The parent JSON element.
     * @param memberName The member of the element to read from.
     * @param fallback   A list of fallback values to use when the child member is not present.
     *
     * @return The list of values read from the JSON.
     */
    default List<T> fromJSONList(JsonObject json, String memberName, List<T> fallback) {
        
        return json.has(memberName) ? this.fromJSONList(json, memberName) : fallback;
    }
    
    /**
     * Reads a list of values from a child element on a JSON object. If the child element is an array each entry will be
     * added to the list. If the child element is an object it will be treated as a list of one.
     *
     * @param json       The parent JSON element.
     * @param memberName The member of the element to read from.
     * @param fallback   A list of fallback values to use when the child member is not present.
     *
     * @return The list of values read from the JSON.
     */
    default List<T> fromJSONList(JsonObject json, String memberName, Supplier<List<T>> fallback) {
        
        return json.has(memberName) ? this.fromJSONList(json, memberName) : fallback.get();
    }
    
    /**
     * Writes a list of values to a JSON element.
     *
     * @param toWrite The values to write.
     *
     * @return The serialized JSON element.
     */
    default JsonElement toJSONList(List<T> toWrite) {
        
        final JsonArray json = new JsonArray();
        toWrite.forEach(t -> json.add(this.toJSON(t)));
        return json;
    }
    
    /**
     * Reads a list of values from a byte buffer.
     *
     * @param buffer The buffer to read data from.
     *
     * @return The list of values read from the buffer.
     */
    default List<T> fromByteBufList(FriendlyByteBuf buffer) {
        
        final int size = buffer.readInt();
        final List<T> list = new ArrayList<>(size);
        
        for(int i = 0; i < size; i++) {
            
            list.add(this.fromByteBuf(buffer));
        }
        
        return list;
    }
    
    /**
     * Writes a list of values to a byte buffer.
     *
     * @param buffer  The buffer to write to.
     * @param toWrite The value to write.
     */
    default void toByteBufList(FriendlyByteBuf buffer, List<T> toWrite) {
        
        buffer.writeInt(toWrite.size());
        toWrite.forEach(t -> this.toByteBuf(buffer, t));
    }
    
    /**
     * Reads a set of values from a JSON element. A JSON array will have each element added to the set. A JSON object
     * will be treated as a set of one element.
     *
     * @param json The JSON element to read from.
     *
     * @return The set of values that were read.
     */
    default Set<T> fromJSONSet(JsonElement json) {
        
        final Set<T> set = new HashSet<>();
        
        if(json.isJsonArray()) {
            
            for(final JsonElement element : json.getAsJsonArray()) {
                
                set.add(this.fromJSON(element));
            }
        } else {
            set.add(this.fromJSON(json));
        }
        
        return set;
    }
    
    /**
     * Reads a set of values from a child member from the parent JSON element.
     *
     * @param json       The parent JSON element.
     * @param memberName The name of the child member.
     *
     * @return The set of values that were read.
     */
    default Set<T> fromJSONSet(JsonObject json, String memberName) {
        
        if(json.has(memberName)) {
            
            return this.fromJSONSet(json.get(memberName));
        }
        
        throw new JsonParseException("Expected member " + memberName + " was not found.");
    }
    
    /**
     * Reads a set of values from a child member from the parent JSON element. If the member does not exist the fallback
     * set will be used.
     *
     * @param json       The parent JSON element.
     * @param memberName The name of the child member.
     * @param fallback   The fallback set to use when the member is not present.
     *
     * @return The set of values that were read.
     */
    default Set<T> fromJSONSet(JsonObject json, String memberName, Set<T> fallback) {
        
        return json.has(memberName) ? this.fromJSONSet(json, memberName) : fallback;
    }
    
    /**
     * Reads a set of values from a child member from the parent JSON element. If the member does not exist the fallback
     * set will be used.
     *
     * @param json       The parent JSON element.
     * @param memberName The name of the child member.
     * @param fallback   The fallback set to use when the member is not present.
     *
     * @return The set of values that were read.
     */
    default Set<T> fromJSONSet(JsonObject json, String memberName, Supplier<Set<T>> fallback) {
        
        return json.has(memberName) ? this.fromJSONSet(json, memberName) : fallback.get();
    }
    
    /**
     * Writes a set of values to a JSON element.
     *
     * @param toWrite The set of values to write.
     *
     * @return The JSON value that was written.
     */
    default JsonElement toJSONSet(Set<T> toWrite) {
        
        final JsonArray json = new JsonArray();
        toWrite.forEach(t -> json.add(this.toJSON(t)));
        return json;
    }
    
    /**
     * Reads a set of values from a byte buffer.
     *
     * @param buffer The byte buffer to read from.
     *
     * @return The set of values that were read from the buffer.
     */
    default Set<T> readByteBufSet(FriendlyByteBuf buffer) {
        
        final int size = buffer.readInt();
        final Set<T> set = new HashSet<>(size);
        
        for(int i = 0; i < size; i++) {
            
            set.add(this.fromByteBuf(buffer));
        }
        
        return set;
    }
    
    /**
     * Writes a set of values from a byte buffer.
     *
     * @param buffer  The byte buffer to write to.
     * @param toWrite The set of values to write.
     */
    default void writeByteBufSet(FriendlyByteBuf buffer, Set<T> toWrite) {
        
        buffer.writeInt(toWrite.size());
        toWrite.forEach(t -> this.toByteBuf(buffer, t));
    }
    
    /**
     * Writes an optional value to a JSON element. If the value is not present a null value will be returned.
     *
     * @param value The optional value to write.
     *
     * @return The written JSON element. If the optional value was not present this will be null.
     */
    default JsonElement fromJSONOptional(Optional<T> value) {
        
        return value.map(this::toJSON).orElse(null);
    }
    
    /**
     * Read an optional value from a child JSON element. If the child JSON member does not exist an empty optional will
     * be used.
     *
     * @param json       The parent JSON element to read from.
     * @param memberName The name of the child member to read.
     *
     * @return An optional containing the value that was read.
     */
    default Optional<T> fromJSONOptional(JsonObject json, String memberName) {
        
        return json.has(memberName) ? Optional.of(this.fromJSON(json.get(memberName))) : Optional.empty();
    }
    
    /**
     * Write an optional value to a JSON object.
     *
     * @param json       The JSON object to write to.
     * @param memberName The name of the member to write the value to.
     * @param value      The value to write.
     */
    default void toJSONOptional(JsonObject json, String memberName, Optional<T> value) {
        
        value.ifPresent(v -> json.add(memberName, this.toJSON(v)));
    }
    
    /**
     * Read an optional value from a byte buffer.
     *
     * @param buffer The buffer to read from.
     *
     * @return An optional containing the value that was read.
     */
    default Optional<T> fromByteBufOptional(FriendlyByteBuf buffer) {
        
        return buffer.readBoolean() ? Optional.of(this.fromByteBuf(buffer)) : Optional.empty();
    }
    
    /**
     * Writes an optional value to the byte buffer.
     *
     * @param buffer   The buffer to write to.
     * @param optional The optional value to write.
     */
    default void toByteBufOptional(FriendlyByteBuf buffer, Optional<T> optional) {
        
        final boolean isPresent = optional.isPresent();
        buffer.writeBoolean(isPresent);
        
        if(isPresent) {
            this.toByteBuf(buffer, optional.get());
        }
    }
    
    /**
     * Writes the value to a string representation of a JSON element.
     *
     * @param toWrite The value to write.
     *
     * @return A string representation of the element when written to JSON.
     */
    default String toJSONString(T toWrite) {
        
        return this.toJSON(toWrite).toString();
    }
    
    /**
     * Reads the value from a JSON string.
     *
     * @param jsonString A string representation of the JSON element to read data from.
     *
     * @return The value read from the JSON data.
     */
    default T fromJSONString(String jsonString) {
        
        return this.fromJSON(JSONHelper.getAsElement(jsonString));
    }
    
}
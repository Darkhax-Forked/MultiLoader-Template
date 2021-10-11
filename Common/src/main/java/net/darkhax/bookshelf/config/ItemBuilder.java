package net.darkhax.bookshelf.config;

import com.google.gson.JsonObject;
import net.darkhax.bookshelf.mixin.item.AccessorItemProperties;
import net.darkhax.bookshelf.serialization.Serializers;
import net.minecraft.world.item.Item;

public class ItemBuilder<T extends Item> extends RegistryEntryConfig<T> {

    private final Item.Properties properties;

    public ItemBuilder(Item.Properties properties, String id) {

        super("items", id);
        this.properties = properties;
    }

    @Override
    void read(JsonObject json) {

        final JsonObject props = json.getAsJsonObject("properties");
        properties.stacksTo(Serializers.INT.fromJSON(props, "stackSize", 64));
        properties.defaultDurability(Serializers.INT.fromJSON(props, "durability", 0));
    }

    @Override
    JsonObject write(JsonObject json) {

        final AccessorItemProperties propsAccess = (AccessorItemProperties) properties;
        final JsonObject props = new JsonObject();
        props.addProperty("stackSize", propsAccess.bookshelf$getMaxStackSize());
        props.addProperty("durability", propsAccess.bookshelf$getMaxDamage());

        json.add("properties", props);
        return json;
    }

    @Override
    T create() {

        return (T) new Item(this.properties);
    }
}
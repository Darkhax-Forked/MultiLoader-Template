package net.darkhax.bookshelf.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.darkhax.bookshelf.mixin.effect.AccessorMobEffectInstance;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Optional;

public class SerializerEffectInstance implements ISerializer<MobEffectInstance>{

    @Override
    public MobEffectInstance fromJSON(JsonElement json) {

        if (json instanceof JsonObject obj) {

            final MobEffect effect = Serializers.MOB_EFFECT.fromJSON(obj, "effect");
            final int duration = Serializers.INT.fromJSON(obj, "duration");
            final int amplifier = Serializers.INT.fromJSON(obj, "amplifier", 0);
            final boolean ambient = Serializers.BOOLEAN.fromJSON(obj, "ambient", false);
            final boolean visible = Serializers.BOOLEAN.fromJSON(obj, "visible", true);
            final boolean showIcon = Serializers.BOOLEAN.fromJSON(obj, "showIcon", true);
            final MobEffectInstance hiddenEffect = Serializers.EFFECT_INSTANCE.fromJSON(obj, "hiddenEffect", (MobEffectInstance) null);

            return new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect);
        }

        throw new JsonParseException("Expected JSON object.");
    }

    @Override
    public JsonElement toJSON(MobEffectInstance toWrite) {

        final JsonObject json = new JsonObject();
        Serializers.MOB_EFFECT.toJSON(json, "effect", toWrite.getEffect());
        Serializers.INT.toJSON(json, "duration", toWrite.getDuration());

        if (toWrite.getAmplifier() != 0) {
            Serializers.INT.toJSON(json, "amplifier", toWrite.getAmplifier());
        }

        if (toWrite.isAmbient()) {
            Serializers.BOOLEAN.toJSON(json,"ambient", toWrite.isAmbient());
        }

        if (!toWrite.isVisible()) {
            Serializers.BOOLEAN.toJSON(json, "visible", toWrite.isVisible());
        }

        if (!toWrite.showIcon()) {
            Serializers.BOOLEAN.toJSON(json, "showIcon", toWrite.showIcon());
        }

        if (((AccessorMobEffectInstance) toWrite).bookshelf$getHiddenEffect() != null) {
            Serializers.EFFECT_INSTANCE.toJSON(json, "hiddenEffect", ((AccessorMobEffectInstance) toWrite).bookshelf$getHiddenEffect());
        }

        return json;
    }

    @Override
    public MobEffectInstance fromByteBuf(FriendlyByteBuf buffer) {

        final MobEffect effect = Serializers.MOB_EFFECT.fromByteBuf(buffer);
        final int duration = Serializers.INT.fromByteBuf(buffer);
        final int amplifier = Serializers.INT.fromByteBuf(buffer);
        final boolean ambient = Serializers.BOOLEAN.fromByteBuf(buffer);
        final boolean visible = Serializers.BOOLEAN.fromByteBuf(buffer);
        final boolean showIcon = Serializers.BOOLEAN.fromByteBuf(buffer);
        final MobEffectInstance hiddenEffect = Serializers.EFFECT_INSTANCE.fromByteBuf(buffer);
        return new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect);
    }

    @Override
    public void toByteBuf(FriendlyByteBuf buffer, MobEffectInstance toWrite) {

    }

    @Override
    public Tag toNBT(MobEffectInstance toWrite) {
        return null;
    }

    @Override
    public MobEffectInstance fromNBT(Tag nbt) {
        return null;
    }
}

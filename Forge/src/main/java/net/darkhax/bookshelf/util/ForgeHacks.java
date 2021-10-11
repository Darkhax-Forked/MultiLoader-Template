package net.darkhax.bookshelf.util;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class exists to get around some of Forge's annoying design choices.
 */
public class ForgeHacks {

    // TODO Consider moving this to a Mixin in the future.
    private static final Method ITEM_ATTRIBUTE_MODIFIER_EVENT_GET_MODIFIABLE = ObfuscationReflectionHelper.findMethod(ItemAttributeModifierEvent.class, "getModifiableMap");

    public static Multimap<Attribute, AttributeModifier> getMutableModifiers(ItemAttributeModifierEvent event) {

        try {

            return (Multimap<Attribute, AttributeModifier>) ITEM_ATTRIBUTE_MODIFIER_EVENT_GET_MODIFIABLE.invoke(event);
        }
        catch (IllegalAccessException | InvocationTargetException e) {

            e.printStackTrace();
            return null;
        }
    }
}

package net.darkhax.bookshelf.event;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * An event listener that will be notified every time the attribute modifiers for an ItemStack are calculated. These
 * listeners have the ability to modify the modifiers and can add or remove modifiers as needed.
 */
public interface IItemAttributeModifierEvent {

    /**
     * Notifies the event listener that the attribute modifiers for an ItemStack are being calculated. This will happen
     * after the vanilla game logic results have been calculated allowing the listener to modify the results as needed.
     *
     * @param stack     The ItemStack that the attributes are being calculated for.
     * @param slot      The equipment slot required for the modifiers to be applied to a user.
     * @param modifiers A supplier that will produce a map of attribute modifiers. The underlying map is immutable and
     *                  will only be unwrapped to a mutable state when requested via the supplier.
     */
    void calculateModifiers(ItemStack stack, EquipmentSlot slot, Supplier<Multimap<Attribute, AttributeModifier>> modifiers);
}

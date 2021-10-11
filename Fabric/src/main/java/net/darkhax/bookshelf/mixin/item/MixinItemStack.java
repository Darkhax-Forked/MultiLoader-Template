package net.darkhax.bookshelf.mixin.item;

import com.google.common.collect.Multimap;
import net.darkhax.bookshelf.function.LazyMutable;
import net.darkhax.bookshelf.event.ItemEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    
    /**
     * Add an event callback for modifying item attribute modifiers. This event is added to provide parity with Forge's
     * version of the event.
     */
    @Inject(method = "getAttributeModifiers", at = @At("RETURN"), cancellable = true)
    public void getAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> callback) {
        
        final var mutableMultimap = LazyMutable.of(callback.getReturnValue());
        ItemEvents.ITEM_MODIFIER.invoker()
                .calculateModifiers((ItemStack) (Object) this, slot, mutableMultimap::getMutable);
        callback.setReturnValue(mutableMultimap.getImmutable());
    }
    
}
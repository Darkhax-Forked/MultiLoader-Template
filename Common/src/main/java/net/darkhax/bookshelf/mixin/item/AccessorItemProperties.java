package net.darkhax.bookshelf.mixin.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Properties.class)
public interface AccessorItemProperties {
    
    @Accessor("maxStackSize")
    int bookshelf$getMaxStackSize();
    
    @Accessor("maxDamage")
    int bookshelf$getMaxDamage();
    
    @Accessor("craftingRemainingItem")
    Item bookshelf$getCraftingRemainingItem();
    
    @Accessor("category")
    CreativeModeTab bookshelf$getCategory();
    
    @Accessor("rarity")
    Rarity bookshelf$getRarity();
    
    @Accessor("foodProperties")
    FoodProperties bookshelf$getFoodProperties();
    
    @Accessor("isFireResistant")
    boolean bookshelf$isFireResistant();
    
    @Accessor("isFireResistant")
    void bookshelf$setFireResistant(boolean isFireResistant);
    
}
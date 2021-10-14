package net.darkhax.bookshelf;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

public class Bookshelf {

    public static final String MOD_ID = "bookshelf";
    public static final String MOD_NAME = "Bookshelf";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final DecimalFormat DECIMAL_2 = new DecimalFormat("##.##");

    public final RegistryHelper registry = Services.REGISTRY.create(MOD_ID);
    public final Content content = new Content(registry);

    public Bookshelf() {

        registry.init();
        Services.EVENTS.onItemTooltip((stack, tooltip, flag) -> tooltip.add(new TextComponent("Test")));
        Services.EVENTS.onItemAttributeModifiers((stack, slot, modifiers) -> {

            if (stack.getItem() == Items.STICK && slot == EquipmentSlot.HEAD) {

                modifiers.get().put(Attributes.ARMOR, new AttributeModifier("test", 5f, AttributeModifier.Operation.ADDITION));
            }
        });
    }
}
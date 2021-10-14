package net.darkhax.bookshelf.util;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.HashMap;
import java.util.Map;

public class PropertyHelper {

    private static final Map<Integer, IntegerProperty> AGE_PROPS = new HashMap<>();

    static {

        AGE_PROPS.put(1, BlockStateProperties.AGE_1);
        AGE_PROPS.put(2, BlockStateProperties.AGE_2);
        AGE_PROPS.put(3, BlockStateProperties.AGE_3);
        AGE_PROPS.put(5, BlockStateProperties.AGE_5);
        AGE_PROPS.put(7, BlockStateProperties.AGE_7);
        AGE_PROPS.put(15, BlockStateProperties.AGE_15);
        AGE_PROPS.put(25, BlockStateProperties.AGE_25);
    }

    /**
     * Gets an age block property or create a new one if the specified max age does not exist yet.
     *
     * @param maxAge The max age to request.
     * @return A block property that represents a maximum age property.
     */
    public static IntegerProperty getAgeProperty(int maxAge) {

        return AGE_PROPS.computeIfAbsent(maxAge, missingAge -> IntegerProperty.create("age", 0, missingAge));
    }
}
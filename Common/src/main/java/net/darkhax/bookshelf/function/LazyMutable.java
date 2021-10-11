package net.darkhax.bookshelf.function;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.function.UnaryOperator;

/**
 * There are several scenarios where the base code may use an immutable object that you wish to conditionally modify
 * such as a map or registry. While converting between these types doing so when it is not needed can be an expensive
 * computation. This object will hold the original immutable value and only make it mutable when needed. Creating the
 * mutable value will also create a new immutable view that can be used later to preserve functionality.
 *
 * @param <T> The type of object to make mutable.
 */
public class LazyMutable<T> {

    /**
     * A function that will take the immutable value and create a mutable value.
     */
    private final UnaryOperator<T> makeMutable;

    /**
     * A function that will take the mutable value and create an immutable one.
     */
    private final UnaryOperator<T> makeImmutable;

    /**
     * The original value.
     */
    private final T original;

    /**
     * The immutable value. This will initially be the original value however once a mutable value has been calculated
     * it will become a new immutable view of the newly mutable value.
     */
    private T immutable;

    /**
     * The mutable value. This will be null until the getter is invoked.
     */
    private T mutable;

    public LazyMutable(T defaultImmutable, UnaryOperator<T> makeMutable, UnaryOperator<T> makeImmutable) {

        this.original = defaultImmutable;
        this.immutable = defaultImmutable;
        this.makeMutable = makeMutable;
        this.makeImmutable = makeImmutable;
    }

    /**
     * Creates a LazyMutable for a Multimap.
     *
     * @param multimap The immutable Multimap.
     * @param <K>      The type of the map key.
     * @param <V>      The type of the map values.
     * @return A LazyMutable for a Multimap.
     */
    public static <K, V> LazyMutable<Multimap<K, V>> of(Multimap<K, V> multimap) {

        return new LazyMutable<>(multimap, HashMultimap::create, Multimaps::unmodifiableMultimap);
    }

    /**
     * Gets the mutable version of the held value. If a mutable value has not been created it will be generated.
     *
     * @return The mutable version of the held value.
     */
    public T getMutable() {

        if (this.mutable == null) {

            this.mutable = this.makeMutable.apply(this.immutable);
            this.immutable = this.makeImmutable.apply(this.mutable);
        }

        return this.mutable;
    }

    /**
     * Gets the immutable view of the held value.
     *
     * @return The immutable view of the value.
     */
    public T getImmutable() {

        return this.immutable;
    }

    /**
     * Gets the original immutable value that was provided.
     *
     * @return The original immutable value.
     */
    public T getOriginal() {

        return this.original;
    }
}
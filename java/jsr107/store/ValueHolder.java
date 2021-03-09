package jsr107.store;

/**
 * The interface Value holder.
 *
 * @param <V> the type parameter
 * @author liufei
 * @date 11 /12/19
 */
public interface ValueHolder<V> {
    /**
     * Value v.
     *
     * @return the v
     */
    V value();
}

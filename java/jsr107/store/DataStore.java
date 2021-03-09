package jsr107.store;

import jsr107.PutStatus;

/**
 * The interface Data store.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @author liufei
 * @date 11 /12/19
 */
public interface DataStore<K, V> {
    /**
     * Get value holder.
     *
     * @param key the key
     * @return the value holder
     * @throws StoreAccessException the store access exception
     */
    ValueHolder<V> get(K key) throws StoreAccessException;

    /**
     * Set.
     *
     * @param key   the key
     * @param value the value
     * @return the put status
     * @throws StoreAccessException the store access exception
     */
    PutStatus put(K key, V value) throws StoreAccessException;

    /**
     * Remove value holder.
     *
     * @param key the key
     * @return the value holder
     * @throws StoreAccessException the store access exception
     */
    ValueHolder<V> remove(K key) throws StoreAccessException;

    /**
     * Clear.
     *
     * @throws StoreAccessException the store access exception
     */
    void clear() throws StoreAccessException;
}

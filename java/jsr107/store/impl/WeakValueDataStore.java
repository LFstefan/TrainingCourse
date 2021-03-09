package jsr107.store.impl;

import jsr107.PutStatus;
import jsr107.store.DataStore;
import jsr107.store.StoreAccessException;
import jsr107.store.ValueHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liufei
 * @date 11/12/19
 */
public class WeakValueDataStore<K, V> implements DataStore<K, V> {
    private ConcurrentHashMap<K, ValueHolder<V>> map = new ConcurrentHashMap<>();

    @Override
    public ValueHolder<V> get(K key) throws StoreAccessException {
        return map.get(key);
    }

    @Override
    public PutStatus put(K key, V value) throws StoreAccessException {
        ValueHolder<V> v = new WeakValueHolder<>(value);
        map.put(key, v);
        return PutStatus.PUT;
    }

    @Override
    public ValueHolder<V> remove(K key) throws StoreAccessException {
        return map.remove(key);
    }

    @Override
    public void clear() throws StoreAccessException {
        map.clear();
    }
}

package jsr107.store.impl;

import jsr107.PutStatus;
import jsr107.store.DataStore;
import jsr107.store.ValueHolder;

/**
 * @author liufei
 * @date 11/12/19
 */
public class BasicDataStore<K, V> implements DataStore<K, V> {
    @Override
    public ValueHolder<V> get(K key) {
        return null;
    }

    @Override
    public PutStatus put(K key, V value) {
        return null;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }
}

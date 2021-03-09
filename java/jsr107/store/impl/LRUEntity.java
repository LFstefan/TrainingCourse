package jsr107.store.impl;

import jsr107.store.ValueHolder;

/**
 * @author liufei
 * @date 11/12/19
 */
public class LRUEntity<K, V extends ValueHolder<?>> {
    K key;
    V value;

    public LRUEntity(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return null;
    }

    public void setValue(V value) {

    }
}

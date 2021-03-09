package jsr107.store.impl;

import jsr107.store.ValueHolder;

/**
 * @author liufei
 * @date 11/12/19
 */
public class BasicValueHolder<V> implements ValueHolder<V> {
    V value;

    public BasicValueHolder(V value) {
        this.value = value;
    }

    @Override
    public V value() {
        return null;
    }
}

package jsr107.store.impl;

import jsr107.store.ValueHolder;

import java.lang.ref.WeakReference;

/**
 * @author liufei
 * @date 11/12/19
 */
public class WeakValueHolder<V> implements ValueHolder<V> {
    private WeakReference<V> v;

    WeakValueHolder(V value) {
        this.v = new WeakReference<V>(value);
    }

    @Override
    public V value() {
        return v.get();
    }
}

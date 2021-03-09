package jsr107.cache;

import jsr107.store.DataStore;
import jsr107.store.StoreAccessException;
import jsr107.store.ValueHolder;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author liufei
 * @date 11/24/2019
 */
public class MyCache<K, V> implements Cache<K, V> {
    private final DataStore<K, V> store;

    public MyCache(final DataStore<K, V> store) {
        this.store = store;
    }

    @Override
    public V get(K k) {
        try {
            ValueHolder<V> value = store.get(k);
            if (Objects.isNull(value)) {
                return null;
            }
            return value.value();
        } catch (StoreAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> set) {
        return null;
    }

    @Override
    public boolean containsKey(K k) {
        return false;
    }

    @Override
    public void loadAll(Set<? extends K> set, boolean b, CompletionListener completionListener) {

    }

    @Override
    public void put(final K k, final V v) {
        try {
            store.put(k, v);
        } catch (StoreAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public V getAndPut(K k, V v) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public boolean putIfAbsent(K k, V v) {
        return false;
    }

    @Override
    public boolean remove(K k) {
        return false;
    }

    @Override
    public boolean remove(K k, V v) {
        return false;
    }

    @Override
    public V getAndRemove(K k) {
        return null;
    }

    @Override
    public boolean replace(K k, V v, V v1) {
        return false;
    }

    @Override
    public boolean replace(K k, V v) {
        return false;
    }

    @Override
    public V getAndReplace(K k, V v) {
        return null;
    }

    @Override
    public void removeAll(Set<? extends K> set) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public void clear() {

    }

    @Override
    public <C extends Configuration<K, V>> C getConfiguration(Class<C> aClass) {
        return null;
    }

    @Override
    public <T> T invoke(K k, EntryProcessor<K, V, T> entryProcessor, Object... objects) throws EntryProcessorException {
        return null;
    }

    @Override
    public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> set, EntryProcessor<K, V, T> entryProcessor, Object... objects) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public CacheManager getCacheManager() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }

    @Override
    public void registerCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

    }

    @Override
    public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }
}

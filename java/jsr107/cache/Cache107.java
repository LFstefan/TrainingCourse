package jsr107.cache;

import jsr107.cache.MyCache;

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
import java.util.Set;

/**
 * @author liufei
 * @date 11/12/19
 * 根据jsr107规则对MyCache进行代理（代理模式）
 */
public class Cache107<K, V> implements Cache<K, V> {
    // TODO 被代理对象，实际操作缓存的对象
    private MyCache<K, V> myCache;
    private CacheManager manager;
    private String cacheName;
    private Configuration<K, V> configuration;

    public Cache107(CacheManager manager, String cacheName, Configuration<K, V> configuration) {
        this.manager = manager;
        this.cacheName = cacheName;
        this.configuration = configuration;
    }

    @Override
    public V get(K k) {
        return myCache.get(k);
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
    public void put(K k, V v) {
        myCache.put(k, v);
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
        myCache.remove(k);
        return true;
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
        return cacheName;
    }

    @Override
    public CacheManager getCacheManager() {
        return manager;
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

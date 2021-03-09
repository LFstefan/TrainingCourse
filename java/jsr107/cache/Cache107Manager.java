package jsr107.cache;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liufei
 * @date 11/12/19
 * 客户端实例管理层，负责客户端实例的创建，销毁，获取
 */
public class Cache107Manager implements CacheManager {
    // 使用ConcurrentHashMap来模拟缓存池
    ConcurrentHashMap<String, Cache107<?, ?>> caches = new ConcurrentHashMap<>();

    @Override
    public CachingProvider getCachingProvider() {
        return null;
    }

    @Override
    public URI getURI() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    synchronized public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C confifuration) throws IllegalArgumentException {
        if (isClosed()) {
            throw new IllegalStateException();
        }
        // 检查缓存实例名称、配置信息是否为空
        // checkNotNull(cacheName, "cacheName");
        // checkNotNull(confifuration, "confifuration");
        Cache107<?, ?> cache = caches.get(cacheName);
        if (Objects.isNull(cache)) {
            cache = new Cache107<>(this, cacheName, confifuration);
            caches.put(cache.getName(), cache);
            return (Cache<K, V>) cache;
        } else {
            throw new CacheException("a cache named " + cacheName + " already exist!");
        }
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyClass, Class<V> valueClass) {
        if (isClosed()) {
            throw new IllegalStateException();
        }
        // 检查缓存实例名称、配置信息是否为空
        // checkNotNull(keyClass, "keyType");
        // checkNotNull(valueClass, "valueType");
        Cache107<?, ?> cache = caches.get(cacheName);
        if (Objects.isNull(cache)) {
            return null;
        } else {
            Configuration<?, ?> configuration = cache.getConfiguration(Configuration.class);
            if (configuration.getKeyType() != null && configuration.getValueType().equals(keyClass)) {
                return (Cache<K, V>) cache;
            } else {
                // 类型不一致
                throw new ClassCastException();
            }
        }

    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) {
        return null;
    }

    @Override
    public Iterable<String> getCacheNames() {
        return null;
    }

    @Override
    public void destroyCache(String s) {

    }

    @Override
    public void enableManagement(String s, boolean b) {

    }

    @Override
    public void enableStatistics(String s, boolean b) {

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


}

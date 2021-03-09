package jsr107;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * @author liufei
 * @date 11/24/2019
 */
public class CacheTest {

    public static void main(String[] args) {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager manager = provider.getCacheManager();
        Cache<String, User> cache = manager.createCache("test", new MutableConfiguration<>());
        String key = "KEY";
        User user = new User() {{
            setName("liufei");
        }};
        cache.put(key, user);
        System.out.println(cache.get(key).getName());
    }
}

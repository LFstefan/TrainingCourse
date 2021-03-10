package dcits.liufein.commands;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * author liufein
 * date 2019/6/14
 * 散列：键值对(key，value均为字符串类型)，返回map类型
 * hmget：获取一个/多个key对应的value值，hmset：设置一个/多个key对应的value值
 * hdel：删除一个/多个key，hlen：返回散列的数量
 * hexists：是否存在，hkeys：返回所有的key值（不包括value值），hvals：返回所有的value值
 * hgetall：返回所有的key和value值，hincrby：自增指定值
 */
public class HashTest {
    public void opteration(){
        Jedis conn = RedisPool.getConn();
        Map map = new HashMap();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        conn.hmset("Hash",map);
        System.out.println(conn.hmget("Hash","key1"));
        conn.hdel("Hash","key3");
        System.out.println(conn.hexists("Hash","key2"));
        conn.hgetAll("Hash").forEach((k,v)->{
            System.out.println("key=" + k + " value=" + v);
        });
    }
}

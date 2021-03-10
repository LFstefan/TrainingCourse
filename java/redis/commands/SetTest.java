package dcits.liufein.commands;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * author liufein
 * date 2019/6/13
 * 集合：去重版的列表
 * sadd：添加一个或者多个元素到集合中，srem：从集合中移除一个或者多个元素，sismember：元素是否存在于集合中
 * scard：返回集合元素数量，smembers：返回集合所有元素，srandmember：随机返回集合中指定个数元素，count为正不可重复，否可重复
 * spop：随机移除集合一个元素，smove：从source集合中移除添加到target中
 * 组合运算
 * sdiff：返回第一个集合有，其他结合没有的元素，差集
 * sdiffstore：把sdiff的结果存储到指定target中
 * sinter：返回所有集合公共的元素，交集
 * sinterstore：
 * sunion：返回所有集合所有元素，并集
 * sunionstore：
 */
public class SetTest {
    public void opteration(){
        Jedis conn = RedisPool.getConn();
        conn.sadd("Set:007","set01","set02","set03");
        System.out.println(conn.sismember("Set:007","set01"));
        conn.srem("Set007","set02");
        conn.smembers("Set007").forEach((e)->{
            System.out.println(e);
        });
    }
}

package dcits.liufein.commands;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * author liufein
 * date 2019/6/14
 * 有序集合：可排序的哈希Hash，key为字符串，value为数字，
 * zadd：添加一个，zrem：删除一个，zcard：返回成员数量，zincrby：value增加指定值，zcount：返回指定范围内的成员个数
 * zrank：返回成员在排序集合中的排名，zscore：返回成员的value值，zrang：返回指定范围内的key（也可以key，value一起返回）
 * zrevrank：返回成员在排序集合中的逆序排名（默认排序value从小到大，逆序为从大到小）
 * zrangebyscore：按照value值范围取key
 * ...
 * 所有z开头的基本均为有序集合的命令，该类型灵活度最高，命令也最多
 * 有序集合执行交，并，和等操作时因为会涉及到value值，所以比较复杂，有具体对应的规则
 */
public class ZsetTest {
    public void opteration(){
        Jedis conn = RedisPool.getConn();
        conn.zadd("SortSet",10,"member1");
        conn.zadd("SortSet",20,"member2");
        conn.zadd("SortSet",30,"member3");
        System.out.println(conn.zcard("SortSet"));
        System.out.println(conn.zcount("SortSet",5,10));
    }
}

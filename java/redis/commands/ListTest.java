package dcits.liufein.commands;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * author liufein
 * date 2019/6/13
 * 列表：可以允许从两端存取元素，元素类型均为字符串
 * rpush：右端推入，lpush：左端推入，rpop：右端弹出，lpop：左端弹出
 * lindex：弹出指定偏移量的元素，lrange：弹出指定区间的元素（闭区间包含首尾），ltrim：只保留指定区间的元素
 * 阻塞式列表：
 * blpop：参数为多个列表，返回第一个不为空的列表的最左端元素，也可以阻塞等待指定时间来等可弹出元素的出现
 * brpop：
 * rpoplpush：弹出source列表中最右端元素放入target列表中最左端
 * brpoplpush：阻塞等地可弹出元素出现...弹出source列表中最右端元素放入target列表中最左端
 * 使用列表可以降低内存
 */
public class ListTest {
    public static void operation(){
        Jedis conn = RedisPool.getConn();
        conn.lpush("List","left1");
        conn.lpush("List","left2");
        conn.rpush("List","right1");
        conn.rpush("List","right2");
        conn.lindex("",2);
        conn.lrange("",1,4);

        conn.blpop("list1","List","list2");
        conn.blpop(10,"list1","List","list2");

        conn.rpoplpush("List","target");
        System.out.println(conn.lpop("target"));
    }
}

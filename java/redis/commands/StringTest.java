package dcits.liufein.commands;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * author liufein
 * date 2019/6/11
 * 字符串类型可以存储三种类型的值
 * 字符串
 * 整数---long
 * 浮点数---double
 * 整数，浮点数-----自增，自减，加N，减N（若key不存在，新建为0）incr，decr，incrby，decrby
 * 字符串-----处理字串和二进制位，append追加，getrange获取指定子串，setrange设置指定子串，getbit获取指定位，
 *      setbit设置指定位，bitcount统计位为1的个数，bitop对多个key执行并，或，异或等操作（字符串的越界部分会被视为空串）
 *
 */
public class StringTest {
    public static void operation(){
        Jedis conn = RedisPool.getConn();
        System.out.println("-----整数-----");
        conn.set("整数","10");
        conn.incr("整数");
        conn.incrBy("整数",10);
        conn.decr("整数");
        conn.decrBy("整数",5);
        System.out.println(conn.get("整数"));

        System.out.println("-----浮点数-----");
        conn.set("浮点数","10.00");
        conn.set("浮点数","10");
        conn.incr("浮点数");
        conn.incrBy("浮点数",10);
        conn.decr("浮点数");
        conn.decrBy("浮点数",5);
        System.out.println(conn.get("浮点数"));

        System.out.println("-----字符串-----");
        conn.set("字符串","Hello ");
        conn.append("字符串","World");
        System.out.println(conn.getrange("字符串",3,5));
        conn.setrange("字符串",5,"---");
        System.out.println(conn.get("字符串"));

        System.out.println("-----位值-----");
        conn.set("位值","8");
        System.out.println(conn.getbit("位值",4));
        conn.setbit("位值",3,true);
        System.out.println(conn.get("位值"));
        System.out.println(conn.bitcount("位值"));
    }
    public static void main(String[] args){
        operation();
    }
}

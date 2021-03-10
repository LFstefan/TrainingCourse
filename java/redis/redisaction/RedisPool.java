package dcits.liufein.redisaction;

import redis.clients.jedis.Jedis;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author liufein
 * date 2019/5/24
 */
public class RedisPool {
    private static final Semaphore semaphore = new Semaphore(10);
    private static ReentrantLock lock = new ReentrantLock();
    private static Jedis conn;

    public static Jedis getConn(){
        try {
            if (conn == null) {
                lock.lock();
                semaphore.acquire();
                conn = new Jedis("54.250.153.192", 9679);
                conn.auth("7b5af799e279e6a0c8fdfe13acaee5a0");
                semaphore.release();
                lock.unlock();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            return conn;
        }
    }
}

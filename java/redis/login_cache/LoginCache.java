package dcits.liufein.login_cache;

import dcits.liufein.utils.Utils;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The type Login cache.
 *
 * @author liufein
 * @date 2019 /6/1 登录缓存(token作為唯一識別驗證，可以是手機號，郵箱等) 传统关系型数据库每秒可以执行200-2000个数据行的维护操作（插入，更新，删除） 使用zset保存最近登录的一千万个用户:recent(token:timestamp) 使用 zset保存最新的25個瀏覽歷史記錄:viewed:token(item:timestamp) 使用hash保存所有登錄用戶緩存:login(token:user)
 */
public class LoginCache {
    /**
     * 用户缓存限制，只保留最新的1000W个用户登录缓存
     */
    private static final int USER_CACHE_LIMIT = 10000000;

    /**
     * 緩存中查看用戶登錄狀態
     *
     * @param conn  连接池
     * @param token 登陆密钥
     * @return the boolean
     */
    boolean checkToken(Jedis conn, String token) {
        return conn.hget("login", token) != null;
    }

    /**
     * 更新登錄緩存，同時更新瀏覽歷史
     *
     * @param conn  连接池
     * @param user  用户
     * @param token 登陆密钥
     * @param item  浏览记录
     */
    void updateToken(Jedis conn, String user, String token, String item) {
        long timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
        //跟新hash表中登錄信息
        conn.hset("login", token, user);
        //更新或者新增token最後一次的登錄時間
        conn.zadd("recent", timestamp, token);
        //item表示浏览过的商品，记录浏览历史,根據時間只保留最新的25个
        if (item != null) {
            conn.zadd("viewed:" + token, timestamp, item);
            conn.zremrangeByRank("viewed:" + token, 0, -26);
            /* 记录所有商品的浏览次数（放入排序集合中）
               todo 这里浏览次数采用负数是因为zset排序集合是遵循从小到大，采用负数可以将浏览次数最多商品页面的排在最前面
             */

            conn.zincrby("viewed:", -1, item);
        }
    }

    /**
     * Rescale viewed.定期删减商品缓存页面，并更新浏览次数
     *
     * @param conn the conn
     */
    public void rescaleViewed(Jedis conn) {
        conn.zremrangeByRank("viewed:", 0, -20001);
        conn.zinterstore("viewed:", "{'viewed:': .5}");
    }

    /**
     * Schedule time.
     *
     * @param conn the conn
     */
    public void scheduleTime(Jedis conn) {
        // 没五分钟执行一次rescaleViewed方法
        Utils.createThreadPool().scheduleWithFixedDelay(() -> rescaleViewed(conn), 0L, 5L, TimeUnit.MINUTES);
        // 每秒执行一次登陆缓存清理操作
        Utils.createThreadPool().scheduleWithFixedDelay(() -> clean(conn), 0L, 1L, TimeUnit.SECONDS);
    }


    /**
     * 定時清理緩存，啟動一個後台線程輪詢（每秒執行一次）保證只保留最新的1000W個用戶緩存
     * todo 也可以使用timer定时任务实现轮询，设置每秒执行一次
     *
     * @param conn 连接池
     */
    public void cleanSessions(Jedis conn) {
/*        //啟動輪詢線程
        while (true) {
            long size = conn.zcard("recent");
            if (size <= USER_CACHE_LIMIT) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            //每次輪詢最多清理100個用戶緩存
            long endKey = Math.min(size, 100);
            Set<String> tokens = conn.zrange("recent", 0, endKey - 1);
            String[] removeKeys = new String[tokens.size()];
            int i = 0;
            for (String token : tokens) {
                removeKeys[i++] = "viewed:" + token;
            }
            //清理用戶所有相關緩存
            conn.del(removeKeys);
            conn.hdel("login", tokens.toArray(new String[]{}));
            conn.zrem("recent", tokens.toArray(new String[]{}));
        }*/
    }

    /**
     * Clean.使用定时任务执行定期清理过期用户缓存
     *
     * @param conn the conn
     */
    public void clean(Jedis conn) {
        long size = conn.zcard("recent");
        if (size >= USER_CACHE_LIMIT) {
            //每次最多清理100個用戶緩存
            long endKey = Math.min(size, 100);
            Set<String> tokens = conn.zrange("recent", 0, endKey - 1);
            String[] removeKeys = new String[tokens.size()];
            int i = 0;
            for (String token : tokens) {
                removeKeys[i++] = "viewed:" + token;
            }
            //清理用戶所有相關緩存
            conn.del(removeKeys);
            conn.hdel("login", tokens.toArray(new String[]{}));
            conn.zrem("recent", tokens.toArray(new String[]{}));
        }

    }

}

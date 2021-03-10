package dcits.liufein.login_cache;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * @author liufein
 * @date 2019/6/4
 */
public class Main {
    public static Jedis conn = RedisPool.getConn();

    public static void main(String[] args) {
        LoginCache loginCache = new LoginCache();
        /**
         * 模擬用戶登陸,第一次直接訪問服務器登陸成功後將登陸信息緩存到redis中，服務器生成一個唯一識別符token返回給用戶，
         * 用戶之後的每次請求都將攜帶該token進行請求
         */
        String user = "18230165039";
        String password = "123456";
        serverHandler(user, password, null, loginCache);
    }

    static String serverHandler(String user, String password, String token, LoginCache loginCache) {
        if (token == null || "".equals(token.trim())) {
            //服務器驗證登錄信息後緩存到redis中
            if ("18230165039".equals(user.trim()) && "123456".equals(password.trim())) {
                token = String.valueOf(user.hashCode());
                loginCache.updateToken(conn, user, token, null);
                return token;
            } else {
                System.out.println("登錄失敗，請重試！");
            }
        } else {
            if (loginCache.checkToken(conn, token)) {
                loginCache.updateToken(conn, user, token, "Mac Pro");
            } else {
                serverHandler(user, password, null, loginCache);
            }
        }
        return "";
    }
}

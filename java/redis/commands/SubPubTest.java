package dcits.liufein.commands;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author liufei
 * @date 11/3/2019
 * 发布/订阅命令
 * TODO 如何处理消息积压，如何实现消息的可靠性传输
 */
public class SubPubTest {
    public static final String CHANNEL_ONE = "channel_1";

    public void subscribeChannel(Jedis conn) {
        conn.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
                System.out.println(message);
            }
        }, CHANNEL_ONE);
    }

    public void publishMessage(Jedis conn) {
        conn.publish(CHANNEL_ONE, "Hello World !");
    }

    public static void main(String[] args) {
        SubPubTest test = new SubPubTest();
        test.publishMessage(RedisPool.getConn());
        test.subscribeChannel(RedisPool.getConn());
    }
}

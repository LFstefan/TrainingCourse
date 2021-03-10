package dcits.liufein.redisaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedisActionApplication {

    public static void main(String[] args){
//        Jedis jedis = new Jedis("10.4.79.17",9679);
//        jedis.set("Hello","World");
//        jedis.close();
//        SpringApplication.run(RedisActionApplication.class, args);
        System.out.println(System.currentTimeMillis());

    }
}

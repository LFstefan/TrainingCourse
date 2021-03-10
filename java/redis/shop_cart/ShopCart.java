package dcits.liufein.shop_cart;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * author liufein
 * date 2019/6/5
 * 购物车缓存，可以和登录缓存放在一起
 * 主要记录购买商品的信息和数量
 */
public class ShopCart {
    private static final int USER_CACHE_LIMIT = 10000000;
    private static final String CART_PRE = "cart:";

    /**
     * 添加购物车
     * @param conn 连接池
     * @param token 登陆密钥
     * @param item 购买商品
     * @param count 购买商品数量
     */
    public void add2Cart(Jedis conn,String token,String item,int count){
        int oldCount = conn.hget(CART_PRE+token,item)!=null ? Integer.valueOf(conn.hget(CART_PRE+token,item)) : 0;
        count += oldCount;
        if(count==0){
            conn.hdel(CART_PRE+token,item);
        }else{
            conn.hset(CART_PRE+token,item,String.valueOf(count));
        }
    }

    /**
     * Truncate cart.清空购物车
     *
     * @param conn  the conn
     * @param token the token
     */
    public void truncateCart(Jedis conn, String token) {

    }

    public void rem2Cart(Jedis conn,String token,String item,int count){
        if(count!=0){
            conn.hdel(CART_PRE+token,item);
        }else{
            String newCount = String.valueOf(Integer.valueOf(conn.hget(CART_PRE+token,item))-count);
            conn.hset(CART_PRE+token,item,newCount);
        }
    }
    public void rem2Cart(Jedis conn,String token,String item,String count){
        if(count!=null&&!("".equals(count.trim()))){
            rem2Cart(conn,token,item,Integer.valueOf(count));
        }else{
            rem2Cart(conn,token,item,0);
        }
    }

    public void cleanSessions(Jedis conn) throws InterruptedException {
        //啟動輪詢線程
        while(true){
            long size = conn.zcard("recent");
            if(size <= USER_CACHE_LIMIT){
                Thread.sleep(1);
                continue;
            }
            //每次輪詢最多清理100個用戶緩存
            long end_key = Math.min(size,100);
            Set<String> tokens = conn.zrange("recent",0,end_key-1);
            String[] remove_keys = new String[tokens.size()];
            int i = 0;
            for(String token : tokens){
                remove_keys[i++] = "viewed:" + token;
                //登录缓存的基础上，清理退出用户的浏览历史记录的同时删除购物车缓存
                remove_keys[i++] = "cart:" + token;
            }
            //清理用戶所有相關緩存
            conn.del(remove_keys);
            conn.hdel("login",(String[]) tokens.toArray());
            conn.zrem("recnet",(String[]) tokens.toArray());
        }
    }

}

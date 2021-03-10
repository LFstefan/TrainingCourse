package dcits.liufein.web_page;

import dcits.liufein.utils.TimeUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Date;
import java.util.Set;

/**
 * author liufein
 * date 2019/6/6
 * web页面缓存
 * 网站的大部分页面其实是静态页面，并不需要动态加载生成，所以可以缓存起来，（设置expire time为五分钟或者几分钟）
 * 在请求之前或者之后添加一层缓存从层，来识别那些请求属于静态页面，直接从缓存读取，减轻应用服务器的负担，加快相应速度
 * <p>
 * 页面缓存技巧，由于直接将页面转为字符串后，页面的格式原因会导致字符串中存在大量的冗余内容，例如：空格等，所以有必要对
 * 转化后的字符串进行优化，或者对页面模板进行优化
 * <p>
 * 缓存浏览次数最多的一万个商品页面，不断调整浏览次数，使得那些新晋商品也能被缓存
 */
public class WebPage {

    /**
     * 静态页面缓存
     *
     * @param conn    the conn
     * @param request the request
     * @param call    CallBack 模拟从库中拉取数据
     * @return page page
     */
    public Page cacheRequest(Jedis conn, String request, CallBack call) {
        //判定是否可以缓存该页面，否直接查库
        if (!isCacheable(conn, request)) {
            return call.callback(request);
        }
        String pageKey = "page:" + request.hashCode();
        //判定缓存中是否还存在该页面的缓存，存在查找返回，否查库返回
        String content = isCached(conn, pageKey) ? conn.get(pageKey) : Page2String(call.callback(request));
        return String2Page(content);
    }

    /**
     * 促销动态页面行数据缓存
     * 促销页面的缓存会导致用户看到错误的数据，但是特价商品的库存剩余数量每次都从库中拉取更新的话
     * 会增加数据库的压力，所以我们将商品的库存数量缓存到redis中，然后设置更新时间为50毫秒
     * 数据库中库存的扣减操作，一定要保证不能出现库存小于0的情况
     * update goods_table set stock = (stock - num) where stock >= num;
     *
     * @param conn  the conn
     * @param rowId the row id
     * @param delay the delay
     */
    public void scheduleRowCache(Jedis conn, String rowId, int delay) {
        //delay有序集合负责记录每一行数据的更新间隔时间
        conn.zadd("delay", delay, rowId);
        //schedule有序集合负责记录行数据的缓存时间（实际记录的是行数据缓存的到期时间）
        conn.zadd("schedule", TimeUtils.Date2TimeStamp(new Date()), rowId);
    }

    /**
     * 后台守护线程每隔50毫秒轮询一次
     *
     * @param conn the conn
     * @param call the call
     */
    public void rowCache(Jedis conn, CallBack call) {
        while (true) {
            //schedule根据行数据缓存到期时间正序排序，找到最近需要更新的行数据key和value
            Set<Tuple> rows = conn.zrangeWithScores("schedule", 0, 0);
            Tuple row = rows.iterator().next();
            long timestamp = TimeUtils.Date2TimeStamp(new Date());
            /*
             *   schedule有序集合为空，说明没有需要更新的行数据缓存；若有,其value值缓存到期时间如果比现在大，
             *   说明还不到更新的时候；这两种情况线程直接睡眠50毫秒在继续轮询
             */
            if (rows == null || row.getScore() > timestamp) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            String rowId = row.getElement();
            int delay = conn.zscore("delay", rowId).intValue();
            //delay更新时间间隔小于0说明行数据是实时的，不可缓存，因此清理掉所有相关缓存
            if (delay < 0) {
                conn.zrem("delay", rowId);
                conn.zrem("schedule", rowId);
                conn.del("inv:" + rowId);
                continue;
            }
            //从库中拉取数据更新缓存内容和下次更新时间（缓存中存储的行数据为json格式的字符串）
            String jsonRow = call.data2Json(rowId);
            conn.zadd("schedule", timestamp + delay, rowId);
            conn.set("inv:" + rowId, jsonRow);
        }
    }

    /**
     * Can cache.判定商品页面是否需要缓存
     *
     * @param conn    the conn
     * @param request the request
     */
    public void canCache(Jedis conn, String request) {
        if (isCacheable(conn, request)) {
            return;
        }
        // 拿到商品页面浏览次数排名
        long rank = conn.zrank("viewed:", String.valueOf(request.hashCode()));
        // todo 根据排名判定是否需要缓存该商品页面
    }

    /**
     * 是否可缓存
     *
     * @param conn    the conn
     * @param request the request
     * @return boolean boolean
     */
    public boolean isCacheable(Jedis conn, String request) {
        return true;
    }

    /**
     * 是否已缓存
     *
     * @param conn    the conn
     * @param pageKey the page key
     * @return boolean boolean
     */
    public boolean isCached(Jedis conn, String pageKey) {
        return conn.get(pageKey) != null;
    }

    /**
     * 字符串转成web页面
     *
     * @param content the content
     * @return page page
     */
    public Page String2Page(String content) {
        //处理细节此处不做说明
        return new Page() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }

    /**
     * web页面转成字符串
     *
     * @param page the page
     * @return string string
     */
    public String Page2String(Page page) {
        //处理细节此处不做说明
        return "";
    }
}

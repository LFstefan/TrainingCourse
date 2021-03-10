package dcits.liufein.article_vote;

import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static dcits.liufein.utils.TimeUtils.Date2TimeStamp;

/**
 * @author liufein
 * @date 2019/5/24
 * 网站每天发布1000篇文章，获赞200的为高质量文章，每天表彰高质量文章的前50，放到榜首100位一天;
 * 文章发布一周之后不再进行投票，分值固定；时间格式存储为
 * 文章详细内容使用hash保存，
 * 按照时间排序的文章列表zset保存
 * 按照分值排序的文章列表zset保存
 * 文章已投票用户名单set保存
 */
class Article {

    private static final int ONE_WEEK_IN_SECONDS = 7 * 24 * 60 * 60;
    private static final int VOTE_SCORE = 432;

    private static final String TIME = "time";

    /**
     * 文章投票
     *
     * @param conn    连接池
     * @param user    文章发布人
     * @param article 文章
     */
    void articleVote(Jedis conn, String user, String article) {
        // 判定文章是否可以继续投票，文章发布一周后不可再进行投票操作
        long cutoff = Date2TimeStamp(new Date()) - ONE_WEEK_IN_SECONDS;
        if (conn.zscore(TIME, article) < cutoff) {
            return;
        }
        // 文章标识为：article:id todo article相当于命名空间名，冒号:相当于命名空间名的的分层标识
        String articleId = article.split(":")[1];
        //todo 应当再次开启事务
        System.out.println();
        // 记录被投票文章对应的用户列表
        conn.sadd("voted:" + articleId, user);
        // 文章投票值增加对应分数
        conn.zincrby("score", VOTE_SCORE, article);
        conn.hincrBy(article, "votes", 1);
        //todo 事务关闭
    }

    /**
     * 发布文章
     *
     * @param conn  连接池
     * @param user  发布人
     * @param title 发布文章名
     * @param link  文章链接
     */
    void postArticle(Jedis conn, String user, String title, String link) {
        //生成一个新的文章id（需要初始化一个全局计数器用作文章id自增生成器，保证唯一性，todo 可以从专门的序列服务获取）
        String articleId = String.valueOf(conn.incr("article:"));
        String voted = "voted:" + articleId;
        conn.sadd(voted, user);
        // 记录文章投票的所有用户id，有效期一周，一旦文章分值固定便不再记录
        conn.expire(voted, ONE_WEEK_IN_SECONDS);
        String article = "article:" + articleId;
        LocalDateTime nowDate = LocalDateTime.now();
        String stringDate = nowDate.format(DateTimeFormatter.ofPattern("yy-MM-dd hh:mm:ss"));
        long timeStamp = Timestamp.valueOf(nowDate).getTime();
        Map<String, String> map = new HashMap<>(8);
        // 哈希表记录文章-标题，发布人，发布时间，投票数，文章链接...
        map.put("title", title);
        map.put("poster", user);
        map.put("time", stringDate);
        map.put("votes", "1");
        map.put("link", link);
        conn.hmset(article, map);
        // time记录文章发布时间列表，只不过时间存贮形式为时间戳，方便比较排序
        conn.zadd("time", timeStamp, article);
        // score记录文章投票数结果列表
        conn.zadd("score", timeStamp + VOTE_SCORE, article);
    }

    /**
     * 按照order排序获取前num个文章
     *
     * @param conn  连接池
     * @param order 排序对象，time或者score
     * @param num   前几取值
     * @return 文章详情
     */
    List<Map<String, String>> getArticles(Jedis conn, String order, int num) {
        List<Map<String, String>> articles = new LinkedList<>();
        Set<String> articleIds = conn.zrevrange(order, 0, num);
        articleIds.forEach((articleId) -> articles.add(conn.hgetAll(articleId)));
        return articles;
    }
}

package dcits.liufein.article_vote;

import dcits.liufein.redisaction.RedisPool;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @author liufein
 * @date 2019/5/30
 */
public class Main {
    public static void main(String[] args) {
        Jedis conn = RedisPool.getConn();
        //設置文章id生成基數，全局唯一，避免衝突
        conn.set("article:", "10000");
        Article article = new Article();
        // 先执行文章发布，检测是否写入redis，文章编号和文章唯一id应该有一个对应关系，这里没有给出
        article.postArticle(conn, "liufei", "Redis", "https://Redis.com");
        article.postArticle(conn, "刘飞", "JAVA", "https://JAVA.com");
        article.postArticle(conn, "stefan", "Mysql", "https://Mysql.com");

        // 文章发布后执行投票和获取排序文章
        article.articleVote(conn, "刘飞", "article:10001");
        article.articleVote(conn, "stefan", "article:10001");
        article.articleVote(conn, "liufei", "article:10002");

        List<Map<String, String>> listTime = article.getArticles(conn, "time", 2);
        List<Map<String, String>> listScore = article.getArticles(conn, "score", 3);

        listTime.forEach(System.out::println);
        listScore.forEach(System.out::println);

    }
}

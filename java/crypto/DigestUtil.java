import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

    public static void main(String[] args) {
        System.out.println(md5("Hello World"));
        System.out.println(sha1("Hello World"));
        System.out.println(sha256("Hello World"));
        System.out.println(hmacSha1("Hello World", "key"));
        System.out.println(hmacSha256("Hello World", "key"));
    }

    /**
     * Md 5 string.
     *
     * @param content the content
     * @return the string
     */
    public static String md5(String content) {
        try {
            // 获取算法实例
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加密
            byte[] digest = md5.digest(content.getBytes());
            // 返回加密后的十六进制字符串
            return toHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sha 1 string.
     *
     * @param content the content
     * @return the string
     */
    public static String sha1(String content) {
        try {
            // 获取算法实例
            MessageDigest md5 = MessageDigest.getInstance("SHA-1");
            // 加密
            byte[] digest = md5.digest(content.getBytes());
            // 返回加密后的十六进制字符串
            return toHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sha 256 string.
     *
     * @param content the content
     * @return the string
     */
    public static String sha256(String content) {
        try {
            // 获取算法实例
            MessageDigest md5 = MessageDigest.getInstance("SHA-256");
            // 加密
            byte[] digest = md5.digest(content.getBytes());
            // 返回加密后的十六进制字符串
            return toHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Hmac sha 1 string.
     *
     * @param content the content
     * @param key     the key 私钥
     * @return the string
     */
    public static String hmacSha1(String content, String key) {
        try {
            // 获取算法实例
            Mac hmacsha1 = Mac.getInstance("HmacSHA1");
            // 该加密算法需要私钥key，保证绝对的安全性
            SecretKeySpec sk = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            hmacsha1.init(sk);
            // 加密
            byte[] digest = hmacsha1.doFinal(content.getBytes());
            // 返回加密后的十六进制字符串
            return toHexString(digest);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Hmac sha 256 string.
     *
     * @param content the content
     * @param key     the key
     * @return the string
     */
    public static String hmacSha256(String content, String key) {
        try {
            // 获取算法实例
            Mac hmacsha1 = Mac.getInstance("HmacSHA256");
            // 该加密算法需要私钥key，保证绝对的安全性
            SecretKeySpec sk = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            hmacsha1.init(sk);
            // 加密
            byte[] digest = hmacsha1.doFinal(content.getBytes());
            // 返回加密后的十六进制字符串
            return toHexString(digest);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final char[] hex = "0123456789abcdef".toCharArray();
    public static String toHexString(byte[] bytes) {
        if (null == bytes) {
            return null;
        }

        StringBuilder sb = new StringBuilder(bytes.length << 1);

        for(int i = 0; i < bytes.length; ++i) {
            sb.append(hex[(bytes[i] & 0xf0) >> 4])
                .append(hex[(bytes[i] & 0x0f)])
                ;
        }

        return sb.toString();
    }
}
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;

public class JwtFactory {
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Encoder urlEncoder = Base64.getUrlEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();
    private static Base64.Decoder urlDecoder = Base64.getUrlDecoder();

    public static String buildToken(Customer customer) {
        Header header = new Header();
        Payload payload = new Payload();
        payload.setCustomer(customer);
        Signature signature = new Signature();
        String secret = signature.getSecret();

        String head = urlEncoder.encodeToString(JsonUtil.toJson(header).getBytes());
        String body = urlEncoder.encodeToString(JsonUtil.toJson(payload).getBytes());
        body = DigestUtils.md5DigestAsHex((secret + body + secret).getBytes());
        return head + body;
    }

    public static String buildSign(Map<String, String> requestParam) {
        Signature signature = new Signature();
        String secret = signature.getSecret();

        requestParam.remove("sign");
        // 本次签名由秘钥开头，秘钥结束
        StringBuffer temp = new StringBuffer(secret);
        requestParam.forEach((k, v) -> {
            temp.append(k).append("=").append(v).append("&");
        });
        temp.deleteCharAt(temp.length() - 1);
        temp.append(secret);
        // 获得最终签名，与请求签名比较校验
        String sign = DigestUtils.md5DigestAsHex(temp.toString().getBytes());
        return sign;
    }
}

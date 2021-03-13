import java.util.Objects;

public class Signature {
    private String key;
    // 私钥
    private String secret = "789fs79avas798avssd8";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
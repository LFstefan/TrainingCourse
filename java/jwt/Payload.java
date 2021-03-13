import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Payload {
    // 签发人
    private String iss;
    // 过期时间
    private LocalDateTime exp;
    // 主题
    private String sub;
    // 受众
    private String[] aud;
    // 生效时间
    private LocalDateTime nbf;
    // 签发时间
    private LocalDateTime iat;
    // 编号
    private String jti;
    // 自定义内容
    private Customer customer;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public LocalDateTime getExp() {
        return exp;
    }

    public void setExp(LocalDateTime exp) {
        this.exp = exp;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String[] getAud() {
        return aud;
    }

    public void setAud(String[] aud) {
        this.aud = aud;
    }

    public LocalDateTime getNbf() {
        return nbf;
    }

    public void setNbf(LocalDateTime nbf) {
        this.nbf = nbf;
    }

    public LocalDateTime getIat() {
        return iat;
    }

    public void setIat(LocalDateTime iat) {
        this.iat = iat;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
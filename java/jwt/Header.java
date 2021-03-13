import java.util.Objects;

public class Header {
    // 加密算法
    private String alg = "HmacSHA256";
    // 令牌类型
    private String typ = "JWT";

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
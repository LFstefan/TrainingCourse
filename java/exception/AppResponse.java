import java.util.Collection;
import java.util.Objects;

public enum AppResponse implements AppException {
    TRADE_NO_NOT_EXIST("T0001", "订单不存在");

    private String code;
    private String message;

    AppResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public <T> void checkNull(T t) {
        if (Objects.isNull(t)) throw new BusinessException(this);
    }

    @Override
    public <T> void checkNull(Collection<T> collection) {

    }

    @Override
    public void checkTrue(boolean flag) {

    }
}
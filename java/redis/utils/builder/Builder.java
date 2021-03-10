package dcits.liufein.utils.builder;

/**
 * @author liufei
 * @date 10/27/2019
 */
public interface Builder<T> {
    T build();
    void reset();
}

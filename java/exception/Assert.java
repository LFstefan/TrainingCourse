import java.util.Collection;

public interface Assert{
    <T> void checkNull(T t);
    <T> void checkNull(Collection<T> collection);

    void checkTrue(boolean flag);
}
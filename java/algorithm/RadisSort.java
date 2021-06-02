import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RadisSort {
    public static int[] target = {34,54,23,132,654,765,98675,53,2342,423423,6546,523,42,42,14213,4235,63547};
    public static List<Integer>[] bucket = new List[10];

    public static void main(String[] args) {

        int radis = Arrays.stream(target).map(e -> String.valueOf(e).length()).max().getAsInt();
        int n = 1;
        for (int i = 1; i < radis; i++) {
            sort(radis, n);
            n *= 10;
        }
        System.out.println(Arrays.toString(target));
    }

    /**
     * function
     */
    public static  void sort (int radis, int n) {

        for (int i = 0; i < target.length; i++) {
            int p = (target[i]/n)%10;
            if (Objects.isNull(bucket[p])) bucket[p] = new ArrayList<>();
            bucket[p].add(target[i]);
        }
        
        int b = 0;
        for (int i = 0; i < 10; i++) {
            if (Objects.nonNull(bucket[i])) {
                for (int e : bucket[i]) {
                    target[b++] = e;
                }
            }
        }

        Arrays.stream(bucket).forEach(e -> {
            if (Objects.nonNull(e)) e.clear();
        });
    }
}

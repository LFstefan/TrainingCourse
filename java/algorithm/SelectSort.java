import java.util.Arrays;

public class SelectSort {
    public static int[] target = {34,54,23,132,654,765,98675,53,2342,423423,6546,523,42,42,14213,4235,63547};

    public static void main(String[] args) {

        for (int i = 0; i < target.length; i++) {
            int p = selectMin(i, target.length);
            swap(i, p);
        }
        System.out.println(Arrays.toString(target));
    }

    /**
     * function
     */
    public static int selectMin (int start, int end) {
        int min = start;
        for (int i = start; i < end; i++) {
            min = target[min] > target[i] ? i : min;
        }
        return min;
    }

    /**
     * function
     */
    public static void swap (int x, int y) {
        int tmp = target[x];
        target[x] = target[y];
        target[y] = tmp;
    }
}

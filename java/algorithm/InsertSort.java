import java.util.Arrays;

public class InsertSort {
    public static int[] target = {34,54,23,132,654,765,98675,53,2342,423423,6546,523,42,42,14213,4235,63547};

    public static void main(String[] args) {
        int start = 0;
        int end = 0;
        for (int i = 1; i < target.length; i++) {
            adjust(start, end++, i);
        }
        System.out.println(Arrays.toString(target));
    }

    /**
     * function
     */
    public static void adjust (int start, int end, int p) {
        for (int i = start; i <= end; i++) {
            if (target[p] < target[i]){
                int tmp = target[p];
                move(i, end);
                target[i] = tmp;
                break;
            }
        }
    }

    /**
     * function
     */
    public static void move (int start, int end) {
        while (end >= start){
            target[end+1] = target[end];
            end--;
        }
    }
}

import java.util.Arrays;

public class BubbleSort {
    public static int[] target = {34,54,23,132,654,765,98675,53,2342,423423,6546,523,42,42,14213,4235,63547};
    public static boolean flag = true;
    public static void main(String[] args) {

        while (flag){
            bubble();
            if (flag) break;
            flag = true;
        }
        System.out.println(Arrays.toString(target));
    }

    /**
     * function
     */
    public static void bubble () {
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i+1] < target[i]){
                swap(i, i+1);
                flag = false;
            }
        }
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

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {
    public static int[] target = {34,54,23,132,654,765,98675,53,2342,423423,3,6546,523,42,1,42,14213,4235,63547};
    public static Stack<Integer[]> stack = new Stack<>();
    public static void main(String[] args) {
        int base = target[0];
        int base_p = 0;
        System.out.println("递归版本实现");
        quick(base, base_p, base_p, target.length-1);
        System.out.println(Arrays.toString(target));

        System.out.println("非递归版本实现");
        stack.push(new Integer[]{0, target.length - 1});
        while (!stack.empty()){
            Integer[] tmp = stack.pop();
            nonRecursiveQuick(target[tmp[0]], tmp[0], tmp[0], tmp[1]);
        }
        System.out.println(Arrays.toString(target));
    }

    /**
     * 递归快排
     */
    public static void quick (int base, int base_p, int start, int end) {
        if (start == end) return;

        int i = start, j = end;
        while (i != j) {
            if (target[j] < base){
                swap(j, base_p);
                base_p = j;
                while (i != j) {
                    if (target[i] > base) {
                        swap(i, base_p);
                        base_p = i;
                        break;
                    } else i++;
                }
            } else j--;
        }

        quick(target[start], start, start, base_p);
        quick(target[base_p+1], base_p+1, base_p+1, end);
    }

    /**
     * function
     */
    public static void swap (int x, int y) {
        int tmp = target[x];
        target[x] = target[y];
        target[y] = tmp;
    }

    /**
     * 非递归快排
     */
    public static void nonRecursiveQuick(int base, int base_p, int start, int end) {
        int i = start, j = end;
        while (i != j) {
            if (target[j] < base){
                swap(j, base_p);
                base_p = j;
                while (i != j) {
                    if (target[i] > base) {
                        swap(i, base_p);
                        base_p = i;
                        break;
                    } else i++;
                }
            } else j--;
        }

        if (start != end){
            stack.push(new Integer[]{start, base_p});
            stack.push(new Integer[]{base_p + 1, end});
        }
    }
}

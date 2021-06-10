import java.util.Arrays;

public class MergeSort {
    public static int[] target = {34,54,23,132,654,765,98675,53,2342,423423,3,6546,523,42,1,42,14213,4235,63547};
    public static int[] tmp = new int[target.length];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(target));
//        recursiveVersion(0, target.length-1);
//        System.out.println(Arrays.toString(target));

        System.out.println("非递归迭代版本");
        nonRecursiveVersion();
        System.out.println(Arrays.toString(target));
    }

    /**
     * 递归版本
     */
    public static void recursiveVersion (int start, int end) {
        System.out.println(start + "---" + end);
        if (start >= end) return;

        int l_start = start;
        int l_end = ((end - start)>>1) + start;
//        int l_end = (end - start)>>1 + start; 位移运算符优先级低于加减运算符，不加括号会导致无限递归，栈溢出
        int r_start = l_end + 1;
        int r_end = end;
        recursiveVersion(l_start, l_end);
        recursiveVersion(r_start, r_end);

        int p = start;
        while (l_start <= l_end && r_start <= r_end)
            tmp[p++] = target[l_start] > target[r_start] ? target[r_start++] : target[l_start++];
        while (l_start <= l_end)
            tmp[p++] = target[l_start++];
        while (r_start <= r_end)
            tmp[p++] = target[r_start++];
        for (p = start; p <= end; p++)
            target[p] = tmp[p];
    }

    /**
     * 非递归迭代版本
     */
    public static void nonRecursiveVersion () {
        int len = target.length;
        int block = 2;
        while (block < len) {
            for (int i = 0; i < len; i += block) {
                merge(i, Math.min(len-1, (i+block-1)));
            }
            block = block<<1;
        }
        merge(0, len - 1);
    }

    /**
     * function
     */
    public static void merge (int start, int end) {
        int l_start = start;
        int l_end = ((end - start)>>1) + start;
        int r_start = l_end + 1;
        int r_end = end;

        int p = start;
        while (l_start <= l_end && r_start <= r_end)
            tmp[p++] = target[l_start] > target[r_start] ? target[r_start++] : target[l_start++];
        while (l_start <= l_end)
            tmp[p++] = target[l_start++];
        while (r_start <= r_end)
            tmp[p++] = target[r_start++];
        for (p = start; p <= end; p++)
            target[p] = tmp[p];
    }
}

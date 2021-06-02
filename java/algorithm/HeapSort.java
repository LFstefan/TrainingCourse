import java.util.Arrays;

public class HeapSort {

    public static int arr[] = {234, 654, 23, 264, 42678, 435, 54356, 628, 54, 745};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(arr));
        int size = arr.length - 1;
        while (size >= 0){
            heapAdjust(size/2, size);
//            System.out.println("调整后：" + Arrays.toString(arr));
            swap(0, size);
//            System.out.println("交换后：" + Arrays.toString(arr));
            size--;
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 堆调整
     */
    public static void heapAdjust (int start, int end) {

        for (int i = start; i >=0 ; i--){
            int left = 2*i + 1;
            int right = left + 1;

            if (right <= end){
                int position = arr[left] > arr[right] ? left : right;
                if (arr[i] < arr[position]) swap(i, position);
            } else if (left <= end)
                if (arr[i] < arr[left]) swap(i, left);
        }
    }

    /**
     * 交换
     */
    public static void swap (int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
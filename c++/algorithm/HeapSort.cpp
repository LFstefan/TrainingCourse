#include <iostream>
#include <vector>

void swap(int &n, int &m)
{
  int tmp = n;
  n = m;
  m = tmp;
}

void heapAdjust(int arr[], int start, int end)
{
  // std::cout << "调整起始值和边界结束值：" << start << "," << end << std::endl;
  for (size_t i = start; (i >= 0 && i <= end); i--)
  {
    // std::cout << "被调增节点: " << i << std::endl;
    int left = 2 * i + 1;
    int right = left + 1;
    if (right <= end)
    {
      int p = arr[left] > arr[right] ? left : right;
      if (arr[i] < arr[p])
        swap(arr[i], arr[p]);
    }
    else if (left <= end)
    {
      if (arr[left] > arr[i])
      {
        swap(arr[i], arr[left]);
      }
    }
  }
}
int main()
{
  int arr[] = {234, 654, 23, 264, 42678, 435, 54356, 628, 54, 745};
  int arr_size = sizeof(arr) / sizeof(arr[0]);
  // std::cout << "数组大小为：" << arr_size << std::endl;
  int n = arr_size - 1;
  while (n >= 0 && n < arr_size)
  {
    heapAdjust(arr, n / 2, n);
    // for (size_t i = 0; i < arr_size; i++)
    // {
    // std::cout << arr[i] << ",";
    // }
    // std::cout << "数组调整后的大顶堆" << std::endl;
    swap(arr[0], arr[n]);
    // for (size_t i = 0; i < arr_size; i++)
    // {
    // std::cout << arr[i] << ",";
    // }
    // std::cout << "首位调换位置后：" << std::endl;
    n--;
  }
  for (size_t i = 0; i < arr_size; i++)
  {
    std::cout << arr[i] << ",";
  }
  return 0;
}

// int main(){
//     int arr[] = {234,654,23,264,42134,435,54356,628,54,745};
//     int arr_size = sizeof(arr)/sizeof(arr[0]);
//     std::cout << "数组大小为：" << arr_size << std::endl;
//     // 先将数组调整成为一个大顶堆
//     heapAdjust(arr, arr_size/2, arr_size - 1);
//     std::cout << "数组调整后的大顶堆" << std::endl;
//     for (size_t i = 0; i < arr_size; i++)
//     {
//         std::cout << arr[i] << ",";
//     }
//     std::cout << "数组调整后的大顶堆" << std::endl;
//     int n = arr_size - 1;
//     while (n-- > 0)
//     {
//         swap(arr[0], arr[n]);
//         heapAdjust(arr, n/2, n - 1);

//     }
//     for (size_t i = 0; i < arr_size; i++)
//     {
//         std::cout << arr[i] << ",";
//     }
//     return 0;
// }
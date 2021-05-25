#include <iostream>
#include <vector>

std::vector<int> bucket[10];
int position = 1;
static int const_num = 10;

void radis_sort(int arr[], int arr_size);

void radis_sort(int arr[], int arr_size) {
    int n = 0;
    while (n < 5)
    {
        for (size_t i = 0; i < arr_size; i++)
        {
            int bitNum = (arr[i]/position)%10;
            std::cout << bitNum << ",";
            bucket[bitNum].push_back(arr[i]);
        }
        std::cout << "第 " << position << " 位数字" << std::endl;
        position *= 10;
        int p = 0;
        for (size_t i = 0; i < 10; i++)
        {
            for (auto j : bucket[i]){
                arr[p++] = j;
            }
                
        }
        // 清空bucket供下次使用
        for (size_t i = 0; i < 10; i++)
        {
            bucket[i].clear();
        }
        n++;
    }
    for (size_t i = 0; i < arr_size; i++)
    {
        std::cout << arr[i] << "," << std::endl;
    }
}

int main() {
    int array[] = {234,654,23,234,42134,435,54356,654,54,745};
    int arr_size = sizeof(array)/sizeof(array[0]);
    radis_sort(array, arr_size);
    return 0;
}



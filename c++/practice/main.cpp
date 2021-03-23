#include <iostream>
// #include "practice.cpp"
#include "Clazz.h"
using namespace std;

extern int extern_int;
// export template int compare<int>(int e1, int e2);
extern void func(void);

thread_local long thread_arg;

int main()
{

	Clazz clazz;
    // TemplateClazz<int, long> t_Clazz(2,4L);
    TemplateClazz<int> t_Clazz_1(8, 16);
    // TemplateClazz<int, long> t_Clazz_1;
	thread_arg = 66L;
    cout << "Hello, world!" << endl;
    cout << extern_int << endl;
    // cout << compare(3, 5) << endl;
    // cout << compare("123", "456") << endl;
    clazz.age = 5;
    cout << "类函数调用：" << clazz.nextAge();
    cout << "\n";
    cout << "模版类参数字段值：" << t_Clazz_1.t_2;
    cout << "模版类方法调用：" << t_Clazz_1.accumulate(300, 200);
    cout << "模版类方法调用：" << t_Clazz_1.sum();
    cout << "\n";
    return 0;
}
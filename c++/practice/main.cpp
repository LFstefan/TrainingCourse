#include <iostream>
#include "practice.hpp"
#include "Clazz.hpp"
using namespace std;

extern int extern_int;
extern void func(void);

thread_local long thread_arg;

int main()
{

	Clazz clazz;
    TemplateClazz<int> t_Clazz_1(8, 16);
	thread_arg = 66L;
    cout << "Hello, world!" << endl;
    func();
    cout << extern_int << endl;
    cout << "模版函数调用：" << compare(3, 5) << endl;
    cout << "模版函数调用：" << compare("123", "456") << endl;
    clazz.age = 5;
    cout << "类函数调用：" << clazz.nextAge() << endl;
    cout << "模版类参数字段值：" << t_Clazz_1.t_2 << endl;
    cout << "模版类方法调用：" << t_Clazz_1.accumulate(300, 200) << endl;
    cout << "模版类方法调用：" << t_Clazz_1.sum() << endl;
    return 0;
}
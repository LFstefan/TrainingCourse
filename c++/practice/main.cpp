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
	thread_arg = 66L;
    cout << "Hello, world!" << endl;
    cout << extern_int << endl;
    // cout << compare(3, 5) << endl;
    // cout << compare("123", "456") << endl;
    func();
    clazz.age = 5;
    cout << clazz.nextAge();
    cout << "\n";
    return 0;
}
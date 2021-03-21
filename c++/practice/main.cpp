#include <iostream>
using namespace std;

extern int extern_int;
// export template<typename T> int compare(T e1, T e2);
extern void func(void);

thread_local long thread_arg;

int main()
{
	thread_arg = 66L;
    cout << "Hello, world!" << endl;
    cout << extern_int << endl;
    // cout << compare(3, 5) << endl;
    // cout << compare("123", "456") << endl;
    func();
    return 0;
}
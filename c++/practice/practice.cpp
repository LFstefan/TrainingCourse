#include <iostream>
#include "practice.hpp"
using namespace std;
extern thread_local long thread_arg;
int extern_int;

void func(void){
	extern_int = 666;
	cout << "线程内变量传递：" << thread_arg << endl;
}
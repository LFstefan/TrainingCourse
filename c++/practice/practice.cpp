#include <iostream>
#include "practice.hpp"
#define NUM_TRUE 1
#define NUM_FALSE -1

using namespace std;
extern thread_local long thread_arg;
int extern_int;

void func(void){
	cout << "线程内变量传递：";
	cout << thread_arg << endl;
}
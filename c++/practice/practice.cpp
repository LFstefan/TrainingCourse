#include <iostream>
// #include "practice.h"
#define NUM_TRUE 1
#define NUM_FALSE -1

using namespace std;
extern thread_local long thread_arg;
int extern_int;

template<typename T> 
int compare(T e1, T e2)
{
	extern_int = 6;
	// cout << "线程内变量传递：";
	// cout << thread_arg << endl;
    if (e1 > e2)
    	return NUM_TRUE;
    return NUM_FALSE;
}

void func(void){
	cout << "线程内变量传递：";
	cout << thread_arg << endl;
}
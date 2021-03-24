template<typename T> 
int compare(T e1, T e2)
{
	extern_int = 6;
	std::cout << "线程内变量传递：" << thread_arg << std::endl;
    if (e1 > e2)
    	return NUM_TRUE;
    return NUM_FALSE;
}
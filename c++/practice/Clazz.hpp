#include<iostream>
// #include <set>
class Clazz{
public:
	// string name;
	int age;
	long money;
	double weight;

	int nextAge();
	long lastMoney();
	void losingWeight(double decr);
};

template<typename T> class TemplateClazz{
private:
	char *name;
	T t_1;
public:
	T t_2;
	// S s;
	unsigned int max;
	// 声明中中直接实现构造函数方法体，或者和其他方法写在一起，写在具体实现中
	// TemplateClazz(T t_value, S s_value):t_1(t_value),s(s_value) {};
	TemplateClazz(T t_value_1, T t_value_2):t_1(t_value_1),t_2(t_value_2) {};
	T accumulate(T t_value_1, T t_value_2);
	int sum();
};

// 模版类的函数必须和模版类声明写在一起
// template<typename T>
// T TemplateClazz<T>::accumulate(T t_value_1, T t_value_2){
// 	return t_value_1 + t_value_2 + 100;
// }

// template<typename T>
// int TemplateClazz<T>::sum(){
// 	return t_1 + t_2;
// }

// 模版类的函数声明和实现分离实现方式，通过ipp/tpp文件分离具体实现
#include "TemplateClazz.tpp"
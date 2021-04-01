#include<iostream>
#include<string>
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

// 面向对象练习

class BaseClazz
{
private:
	int private_num;
protected:
	int protected_num;
public:
	int public_num;
	BaseClazz(int arg1, int arg2):private_num(arg1),public_num(arg2){};
	// 普通函数
	int normalFunc(std::string name);
	// 虚函数
	virtual void virtualFunc(size_t size, long long super_long_num);
};

class DerivedClazz:public BaseClazz
{
private:
	int private_data;
protected:
	int protected_num;
public:
	int public_data;
	DerivedClazz(int arg1, int arg2, int arg3, int arg4):BaseClazz(arg1,arg2),private_data(arg3),public_data(arg4){};
	int normalFunc(std::string name);
	virtual void virtualFunc(size_t size, long long super_long_num) override;
};

class BaseDerivedClazz:public DerivedClazz
{
private:
	std::string private_name;
protected:
	int protected_num;
public:
	std::string public_name;
	BaseDerivedClazz(int arg1, int arg2, int arg3, int arg4, std::string arg5, std::string arg6):DerivedClazz(arg1,arg2,arg3,arg4),private_name(arg5),public_name(arg6){};
	int normalFunc(std::string name);
	virtual void virtualFunc(size_t size, long long super_long_num) override;
};

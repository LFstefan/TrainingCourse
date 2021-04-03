#include<iostream>
#include<string>
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

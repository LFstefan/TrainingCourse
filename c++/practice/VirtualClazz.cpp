#include "VirtualClazz.hpp"

int BaseClazz::normalFunc(std::string name){
	std::cout << "基类的普通方法: " << name << std::endl;
	return 0;
}

void BaseClazz::virtualFunc(size_t size, long long super_long_num){
	std::cout << "基类的虚函数: " << size * super_long_num << std::endl;
}

int DerivedClazz::normalFunc(std::string name){
	std::cout << "派生类的普通方法: " << name << std::endl;
	return 0;
}

void DerivedClazz::virtualFunc(size_t size, long long super_long_num){
	std::cout << "派生类的虚函数: " << size * super_long_num << std::endl;
}


int BaseDerivedClazz::normalFunc(std::string name){
	std::cout << "派生类的派生类的普通方法: " << name << std::endl;
	return 0;
}

void BaseDerivedClazz::virtualFunc(size_t size, long long super_long_num){
	std::cout << "派生类的派生类的虚函数: " << size * super_long_num << std::endl;
}
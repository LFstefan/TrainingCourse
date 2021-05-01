#include "CDestructor.hpp"

CDestructor::CDestructor(std::string name){
    std::cout << name << std::endl;
}

CDestructor::~CDestructor(){
    std::cout << "基类的析构函数" << std::endl;
}

CDestructoDerive::~CDestructoDerive(){
    std::cout << "派生类的析构函数" << std::endl;
}

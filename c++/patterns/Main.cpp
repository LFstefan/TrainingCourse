#include "Singleton.h"

int main(int argc, char const *argv[]){
    Singleton* s = Singleton::getInstance();
    std::cout << s->name << std::endl;
    Singleton* s1 = Singleton::getInstance();
    std::cout << s1->name << std::endl;

    std::cout << "s 指向的地址为：" << s << std::endl;
    std::cout << "s1 指向的地址为：" << s1 << std::endl;
    delete s;
    delete s1;
    std::cout << s->name << std::endl;
    return 0;
}

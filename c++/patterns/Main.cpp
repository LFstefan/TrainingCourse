#include "Singleton.h"
#include "BridgePattern.hpp"

int main(int argc, char const *argv[]){

    // 单例模式
    // Singleton* s = Singleton::getInstance();
    // std::cout << s->name << std::endl;
    // Singleton* s1 = Singleton::getInstance();
    // std::cout << s1->name << std::endl;

    // std::cout << "s 指向的地址为：" << s << std::endl;
    // std::cout << "s1 指向的地址为：" << s1 << std::endl;
    // delete s;
    // delete s1;
    // 验证指针被delete后的继续元素访问
    // std::cout << s->name << std::endl;

    // 桥接模式
    BookAuthor *author = new BookAuthor("liufei");
    RefinedAbstract *abs = new RefinedAbstract();
    abs->planBook({"设计模式","货币战争","金融理财"}, author);
    abs->releaseBook();
    delete abs;
    delete author;

    return 0;
}

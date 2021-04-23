#include "Singleton.h"

Singleton* Singleton::_instance = 0;

Singleton* Singleton::getInstance(){
    if (_instance == 0){
        _instance = new Singleton();
    }
    return _instance;
}

Singleton::Singleton(){
    name = "liufei";
    std::cout << "creator: " << name << " created singleton instance" << std::endl;
}
Singleton::~Singleton(){
    delete _instance;
}
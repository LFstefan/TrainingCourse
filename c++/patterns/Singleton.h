#include<iostream>

class Singleton
{
private:
    static Singleton* _instance;
protected:
    Singleton();    
public:
    ~Singleton();
    std::string name;
    static Singleton* getInstance();
};

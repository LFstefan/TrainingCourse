#include <iostream>

class CDestructor {
private:
    /* data */
public:
    std::string name;
    CDestructor(std::string n);
    virtual ~CDestructor();
};

class CDestructoDerive: public CDestructor {
private:
    /* data */
public:
    std::string name;
    CDestructoDerive(std::string n, std::string n1):name(n),CDestructor(n1){};
    virtual ~CDestructoDerive();
};
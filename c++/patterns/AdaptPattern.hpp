#include <iostream>

class Zhiliudian
{
private:
    /* data */
public:
    Zhiliudian(/* args */);
    virtual ~Zhiliudian();
    virtual void providerPower() = 0;
};

class ZhiliudianProvider:public Zhiliudian
{
private:
    /* data */
public:
    ZhiliudianProvider(/* args */);
    ~ZhiliudianProvider();
    void providerPower();
};

class Jiaoliudian
{
private:
    /* data */
public:
    Jiaoliudian(/* args */);
    virtual ~Jiaoliudian();
    virtual void providerPower(Zhiliudian *z) = 0;
};

class Adapter:public Jiaoliudian
{
private:
    /* data */
public:
    Zhiliudian *zhiliudian;
    Adapter(/* args */);
    ~Adapter();
    void providerPower(Zhiliudian *z);
};

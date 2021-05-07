#include "AdaptPattern.hpp"

Zhiliudian::Zhiliudian(/* args */)
{
}

Zhiliudian::~Zhiliudian()
{
}

ZhiliudianProvider::ZhiliudianProvider(/* args */)
{
}

ZhiliudianProvider::~ZhiliudianProvider()
{
}

void ZhiliudianProvider::providerPower()
{
    std::cout << "提供直流电" << std::endl;
}

Jiaoliudian::Jiaoliudian(/* args */)
{
}

Jiaoliudian::~Jiaoliudian()
{
}

Adapter::Adapter(/* args */)
{
}

Adapter::~Adapter()
{
}

void Adapter::providerPower(Zhiliudian *z)
{   
    zhiliudian = z;
    zhiliudian->providerPower();
    std::cout << "转化为交流电" << std::endl;
}
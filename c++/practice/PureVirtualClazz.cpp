#include "PureVirtualClazz.hpp"

int Square::getArea(){
    return height * weight;
}

int Square::getVolume(){
    return height * weight * length;
}

int Rectangle::getArea(){
    return height * weight;
}

int Rectangle::getVolume(){
    return height * weight * length;
}

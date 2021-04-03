#include "PureVirtualClazz.hpp"

int Line::getLength(){
    return length;
}

int Square::getArea(){
    return height * weight;
}

int Square::getVolume(){
    return height * weight * length;
}

std::string Square::getName(){
    return name;
}

int Rectangle::getArea(){
    return height * weight;
}

int Rectangle::getVolume(){
    return height * weight * length;
}

std::string Rectangle::getName(){
    return name;
}
#include<iostream>
#include<string>
// 抽象类不能实例化
class Line
{
private:
    /* data */
public:
    virtual int getArea() = 0;
    virtual int getVolume() = 0; 
};

class Shape:public Line
{
private:
    /* data */
public:
    int height;
    int weight;
};

class Square:public Shape
{
private:
    /* data */
public:
    int area;
};

class Rectangle:public Shape
{
private:
    /* data */
public:
    int volume;
};

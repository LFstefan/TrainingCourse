#include<iostream>
#include<string>
// 抽象类不能实例化
class Line
{
private:
    /* data */
public:
    int length;
    int getLength();
    Line(int len):length(len){};
    virtual int getArea() = 0;
    virtual int getVolume() = 0; 
};

class Shape:public Line
{
private:
    /* data */
public:
    std::string name;
    int height;
    int weight;
    Shape(int len, int h, int w, std::string n):Line(len),height(h),weight(w),name(n){};
    virtual std::string getName() = 0;
};

class Square:public Shape
{
private:
    /* data */
public:
    int area;
    Square(int len, int h, int w, int a, std::string n):Shape(len, h, w, n),area(a){};
    int getArea();
    int getVolume();
    std::string getName();
};

class Rectangle:public Shape
{
private:
    /* data */
public:
    int volume;
    Rectangle(int len, int h, int w, int v, std::string n):Shape(len, h, w, n),volume(v){};
    int getArea();
    int getVolume();
    std::string getName();
};

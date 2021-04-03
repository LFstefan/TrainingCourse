#include<iostream>

// #pragma once
// 等效于 _Pragma("once")
// #ifdef _MAIN_H
// #define _MAIN_H

struct Item
{
    char *item_name;
    double item_price;
    int item_num;

    Item(char *name, double price, int num):item_name(name),item_price(price),item_num(num){};

    double getTotalPrice();
};

// #endif

// struct定义的类，函数实现暂时没办法分离到不同文件中
double Item::getTotalPrice(){
    return item_num * item_price;
}


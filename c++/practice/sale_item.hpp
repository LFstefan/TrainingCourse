#include<iostream>
#include<string>
#include<vector>
#include "Item.hpp"

typedef std::vector<int> int_vector;

class Sale_item
{
private:
    int private_var;
public:
    std::string sale_name;
    std::vector<Item> item_vector;
    int_vector int_vector_list;
    std::vector<std::string> string_vector;

    Sale_item();
    Sale_item(std::string name, int_vector int_list):sale_name(name),int_vector_list(int_list){};

    Sale_item &get_my_self(std::string name);
    const Sale_item &get_const_my_self(int element) const;
};

const Sale_item &Sale_item::get_const_my_self(int element) const{
    // int_vector_list.push_back(element);
    return *this;
}

Sale_item &Sale_item::get_my_self(std::string name){
    sale_name = name;
    return *this;
}

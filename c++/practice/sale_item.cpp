#include "sale_item.hpp"

const Sale_item &Sale_item::get_const_my_self(int element) const{
    // int_vector_list.push_back(element);
    return *this;
}

Sale_item &Sale_item::get_my_self(std::string name){
    sale_name = name;
    return *this;
}
#include <iostream>
#include <functional>
#include "practice.hpp"
#include "Clazz.hpp"
// #include "sale_item.hpp"
#include "VirtualClazz.hpp"
#include "PureVirtualClazz.hpp"

using namespace std;

extern int extern_int;
extern void func(void);

thread_local long thread_arg;

void oop_test(BaseClazz &baseClazz){
	baseClazz.normalFunc("hello world");
    baseClazz.virtualFunc(2, 6);
}

void pure_virtual_test(Shape &arg){
    std::cout << arg.getName() << "面积：" << arg.getArea() << std::endl;
    std::cout << arg.getName() << "体积：" << arg.getVolume() << std::endl;
}
// lambda表达式作为函数的参数进行传递，即实现行为参数的传递
std::string lambda_arg_func_test(std::function<std::string (int arg_1, long arg_2)> const &func, std::string name, int val_1, long val_2){
    std::string s = func(val_1, val_2);
    cout << "lambda_arg_func_test: " << name << " --- " << s << endl;
    return name.append(s);
}
std::function<std::string (int arg_1, long arg_2)> lambda_return_func_test(int value){
    return [=](int val_1, long val_2) -> std::string {
        cout << "lambda表达式作为函数返回值返回" << endl;
        cout << "lambda_return_func_test: " << value * val_1 * val_2 << endl;
        return " Hello lambda_return_func_test !";
    };
}

// 左右值引用练习
template <typename T> 
void reference_func_test(T& param) {
    cout << "左值引用传入值：" << param << endl;
    param = 9;
}
template <typename T> 
void reference_func_test_1(T&& param) {
    cout << "右值引用传入值：" << param << endl;
    param = 9;
}

int main()
{

	Clazz clazz;
    TemplateClazz<int> t_Clazz_1(8, 16);
	thread_arg = 66L;
    cout << "Hello, world!" << endl;
    func();
    cout << extern_int << endl;
    cout << "模版函数调用：" << compare(3, 5) << endl;
    cout << "模版函数调用：" << compare("123", "456") << endl;
    clazz.age = 5;
    cout << "类函数调用：" << clazz.nextAge() << endl;
    cout << "模版类参数字段值：" << t_Clazz_1.t_2 << endl;
    cout << "模版类方法调用：" << t_Clazz_1.accumulate(300, 200) << endl;
    cout << "模版类方法调用：" << t_Clazz_1.sum() << endl;

    // Item item("货品", 12.6, 5);
    // cout << "item名称为：" << item.item_name << endl;
    // cout << "item总价为：" << item.getTotalPrice() << endl;

    // Sale_item sale_item("平台", int_vector{1,2,3,4,5,6});
    // Sale_item sale_item_ref = sale_item.get_my_self("平台-1");
    // cout << "sale_item的name值为：" << sale_item.sale_name << "; sale_item_ref的name值为；" << sale_item_ref.sale_name << endl;
    // sale_item_ref.sale_name = "平台-2";
    // cout << "sale_item的name值为：" << sale_item.sale_name << "; sale_item_ref的name值为；" << sale_item_ref.sale_name << endl;
    // Sale_item sale_item_const = sale_item.get_const_my_self(2);
    // sale_item_const.sale_name = "平台-3";
    // cout << "sale_item的name值为：" << sale_item.sale_name << "; sale_item_const的name值为；" << sale_item_const.sale_name << endl;

    // 虚函数练习
    BaseClazz baseClazz(2,4);
    DerivedClazz derivedClazz(2,4,6,8);
    // BaseClazz *baseClazz_1 = &derivedClazz;
    BaseDerivedClazz baseDerivedClazz(2,4,6,8,"hello", "world");
    // BaseClazz *baseClazz_2 = &baseDerivedClazz;
    
    oop_test(baseClazz);
    oop_test(derivedClazz);
    oop_test(baseDerivedClazz);

    Square square(2,3,4,5, "正方形");
    Rectangle rec(6,7,8,9,"长方形");
    pure_virtual_test(square);
    pure_virtual_test(rec);

    // lambda表达式练习
    int arg = 6;
    auto func1 = [arg] () mutable { return ++arg; };
    auto func1_1 = [=] () mutable { return ++arg; };
    auto func2 = [&arg] () { return ++arg; };
    auto func2_1 = [&] () { return ++arg; };
    // 返回结果是条件表达式的时候，结果类型可以自动识别，无需显示指定，其他需要
    // 和java的lambda相比，多了一个捕获列表（即方括号[arg...]）来引用所在函数中定义的局部变量
    // 捕获列表的参数默认是不可变的，使用mutable关键字后可变
    auto resultFunc = [arg] (int param) -> int {
        if (param > 0){
            return arg + param;
        } else 
            return arg - param;
        
    };
    cout <<  "lambda: " << func1() << endl;
    cout <<  "lambda: " << func2() << endl;
    cout <<  "lambda: " << resultFunc(3) << endl;
    cout <<  "lambda: " << resultFunc(-3) << endl;
    // lambda表达式作为函数参数传递练习
    auto lambda_arg_func = [=](int val_1, long val_2) -> std::string {
        cout << "lambda_arg_func: " << arg * val_1 * val_2 << endl;
        return " Hello World!";
    };
    lambda_arg_func_test(lambda_arg_func, "liufei ", 3, 6L);
    lambda_arg_func_test(lambda_return_func_test(23), "kangyaxin", 5, 9L);

    // 引用相关联系
    int num = 5;
    reference_func_test(num);
    reference_func_test_1(5);
    reference_func_test_1(num);
    reference_func_test_1(std::move(num));
    // reference_func_test_1(std::forward(22));
    cout << "num的值为：" << num << endl;

    // 主函数结束
    return 0;
}
#include "BridgePattern.hpp"

BookWritor::~BookWritor(){
    std::cout << " bookwritor has been destory" << std::endl;
}

BookAuthor::~BookAuthor(){
    std::cout << " bookauthor has been destory" << std::endl;
}

std::string BookAuthor::getName(){
    return name;
}

void BookAuthor::startWriteBook(std::string bookName){
    std::cout << name << "编著了" << bookName << std::endl;
} 

Abstract::~Abstract(){
    std::cout << " abstract has been destory" << std::endl;
}

RefinedAbstract::~RefinedAbstract(){
    std::cout << " refinedabstract has been destory" << std::endl;
}

void RefinedAbstract::planBook(std::vector<std::string> bookNames, BookWritor *w){
    writer = w;
    bookList = bookNames;
    for (auto &i : bookList)
    {
        i = "<<" + i + ">>"; 
    }
    for (auto i : bookList)
    {
        writer->startWriteBook(i);
    }
}

void RefinedAbstract::releaseBook(){
    std::cout << "图书发布清单" << std::endl;
    std::string name = writer->getName();
    for (auto book : bookList)
    {
        std::cout << "书名：" << book << "---" << "作者：" << name << std::endl;
    }
}
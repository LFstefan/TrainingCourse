#include <iostream>
#include <vector>

// 实现
class BookWritor
{
private:
    /* data */
public:
    std::string name;
    virtual void startWriteBook(std::string bookName) = 0;
    virtual std::string getName() = 0;
    BookWritor(std::string n):name(n){};
    ~BookWritor();
};

class BookAuthor:public BookWritor
{
private:
    /* data */
public:
    std::string getName();
    void startWriteBook(std::string bookName);
    BookAuthor(std::string n):BookWritor(n){};
    ~BookAuthor();
};

// 抽象
class Abstract
{
private:
    
public:
    BookWritor *writer;
    std::vector<std::string> bookList;
    Abstract(){};
    virtual ~Abstract();
    virtual void planBook(std::vector<std::string> bookNames, BookWritor *w) = 0;
    virtual void releaseBook() = 0;
};

class RefinedAbstract:public Abstract
{
private:
    /* data */
public:
    RefinedAbstract(){};
    ~RefinedAbstract();
    void planBook(std::vector<std::string> bookNames, BookWritor *w);
    void releaseBook();
};

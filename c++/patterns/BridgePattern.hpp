#include <iostream>
#include <vector>

class BookWritor
{
private:
    /* data */
public:
    std::string name;
    virtual std::string getName() = 0;
    BookWritor(std::string n):name(n){};
    ~BookWritor();
};

class BookAuthor:public BookWritor
{
private:
    /* data */
public:
    virtual std::string getName();
    BookAuthor(std::string n):BookWritor(n){};
    ~BookAuthor();
};

class Abstract
{
private:
    
public:
    BookWritor writer;
    Abstract(/* args */);
    ~Abstract();
    virtual void planBook(std::vector<std::string> bookNames, std::vector<std::string> authorNames) = 0;
    virtual void releaseBook() = 0;
};



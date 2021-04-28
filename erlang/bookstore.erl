-module(bookstore).
-export([start/0, getBookNameList/1]).

% BookList = [{pattern, 23.67}, {business, 99.5}, {design, 45.0}].

start() -> "Book Store".

% 获取书名列表
getBookNameList([X|L]) -> 
    {Name, _} = X,
    [{Name, _} = Y || Y <- L].
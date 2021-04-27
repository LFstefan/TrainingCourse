-module(shop1).
-export([total/1]).

total([{what,N}|T]) -> shop:cost(what) * N + total(T);
total([]) -> 0. 
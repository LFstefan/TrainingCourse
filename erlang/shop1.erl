-module(shop1).
-export([total/1]).
-import(shop, [cost/1]).

% L = [{apple, 4}, {milk, 3}]

total([{What,N}|T]) -> cost(What) * N + total(T);
total([]) -> 0. 
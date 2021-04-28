执行erl进入erlang shell命令行界面执行erl文件编译和运行
编译：c(hello).
运行：hello:start().

操作系统命令行执行erl文件编译和运行
编译：erlc hello.erl
运行：erl -noshell -s hello start -s init stop

[X || qualifiler] : X是一个表达式，表示要执行的动作/行数/行为，qualifiler是执行动作需要参数的来源
样例：[A * B || {A,B} <- L]

内置函数手册
http://www.erlang.org/doc/man/erlang.html

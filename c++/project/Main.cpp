#include "Tetris.hpp"


WINDOW *create_newwin(int height, int width, int starty, int startx)
{			//参数分别是，窗口的高、宽，窗口的左上角的坐标（起始坐标）
	WINDOW *local_win;
	local_win = newwin(height, width, starty, startx);
	box(local_win,0,0);	//给新创建的窗口画上边框
	wrefresh(local_win);
	return local_win;
}

void destory_win(WINDOW *local_win)
{
	wborder(local_win, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ');		//原来的*边框改为空白
	wrefresh(local_win);
	delwin(local_win);						//删除窗口
}

extern int game_win_height;
extern int game_win_width;
extern int hint_win_height;
extern int hint_win_width; 

int main()
{
	initscr();						//屏幕初始化，并进入curses模式
	//raw();						//禁止行缓冲，处理挂起、中断或退出等将直接传送给程序去处理而不产生终端信号
	cbreak();						//禁止行缓冲，控制字符将被终端驱动程序解释成其它字符
	noecho();						//禁止输入的字符出现在屏幕上
	curs_set(0);
	keypad(stdscr,TRUE);			//允许使用功能键，为标准屏幕（stdscr）激活了功能键

	refresh();						//刷新终端屏幕
	
	WINDOW *game_win = create_newwin(game_win_height, game_win_width, 0,0);	//创建游戏窗口，设置边框
	wborder(game_win, '*', '*', '*', '*', '*', '*', '*', '*');
	wrefresh(game_win);

	WINDOW *hint_win = create_newwin(hint_win_height, hint_win_width, 0, game_win_width+10);//创建下一个形状提示窗口
	mvprintw(0, game_win_width+10+2,"%s","Next");
	refresh();

	WINDOW *score_win = create_newwin(hint_win_height, hint_win_width, 20, game_win_width+10);//创建分数窗口
	mvprintw(20, game_win_width+10+2,"%s","Score");
	refresh();



	Piece* pp = new Piece;			//创建方块对象
	pp->initial(game_win, score_win);					//初始化方块


	while(1)
	{
		pp->move(game_win, score_win, hint_win);					//移动方块
		if(pp->game_over)			//判断game_over参数是否等于true
			break;
	}

	destory_win(game_win);
	destory_win(hint_win);
	destory_win(score_win);
	delete pp;
	system("clear");

	int row,col;
	getmaxyx(stdscr,row,col);
	mvprintw(row/2,col/2 ,"%s","GAMER OVER ! \n ");
	mvprintw(row/2+2,col/2-2 ,"%s","Wait 5s to return tthe erminal ! \n ");
	refresh();

	sleep(5);
	endwin();
	return 0;
}
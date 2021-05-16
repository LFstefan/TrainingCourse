#include <iostream>
#include <sys/time.h>
#include <sys/types.h>
#include <stdlib.h>
#include <ncurses.h>
#include <unistd.h>

int game_win_height=30;	
int game_win_width=45;	//游戏窗口的尺寸

int hint_win_height=10;
int hint_win_width=20;	//显示下一个方块形状的窗口的尺寸，显示分数的窗口的尺

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

int main(int argc, char **argv)
{
    initscr();
    // printw("hello workd!");

    WINDOW *game_win = create_newwin(game_win_height, game_win_width, 0,0);	//创建游戏窗口，设置边框
	mvprintw(0, game_win_width/2,"%s","Game");
	refresh();

	WINDOW *hint_win = create_newwin(hint_win_height, hint_win_width, 0, game_win_width+10);//创建下一个形状提示窗口
	mvprintw(0, game_win_width+10+2,"%s","Next");
	refresh();

	WINDOW *score_win = create_newwin(hint_win_height, hint_win_width, 20, game_win_width+10);//创建分数窗口
	mvprintw(20, game_win_width+10+2,"%s","Score");
	refresh();

    // refresh();
    sleep(5);
    destory_win(game_win);
    destory_win(hint_win);
    destory_win(score_win);
    endwin();
    return 0;
}
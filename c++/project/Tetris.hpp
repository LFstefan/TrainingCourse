#include <iostream>
#include <sys/time.h>
#include <sys/types.h>
#include <stdlib.h>
#include <ncurses.h>
#include <unistd.h>

class Piece
{
private:
    /* data */
public:
    Piece(/* args */);
    ~Piece();
    int score;
    int shape;
    int next_shape;
    int head_x;
    int head_y;
    int size_h;
    int size_w;
    int next_size_h;
    int next_size_w;
    int box_shape[4][4];
    int next_box_shape[4][4];
    int box_map[30][45];
    bool game_over;
    void initial(WINDOW *hint_win, WINDOW *score_win);
    void set_shape(int &cshape, int box_shape[][4], int&size_w, int&size_h);
    void score_next(WINDOW *game_win, WINDOW *score_win, WINDOW *hint_win);
    void judge(WINDOW *game_win);
    void move(WINDOW *game_win, WINDOW *score_win, WINDOW *hint_win);
    void rotate(WINDOW *game_win);
    bool isaggin();
    bool exsqr(int row);
};

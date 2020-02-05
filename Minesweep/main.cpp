/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: Dustin
 *
 * Created on March 7, 2017, 10:02 PM
 */

#include <Simple_window.h>
#include <Graph.h>
#include <iostream>
#include <FL/fl.h>
#include <FL/Fl_box.h>
#include <FL/Fl_Window.h>
#include <Fl/fl_ask.H>
#include <string>
using namespace std;

class cell {
private:
    int minecount;
    bool state;
    bool flag;
public:
    bool mined;
    int x;
    int y;
    cell(bool m, int a, int b) {
        x = a;
        y = b;
        mined = false;
        state = false;
    }

    cell() {
        state = false;
    }
    Fl_Button *box;

    int num() {
        return minecount;
    }
    
    void setnum(int a) {
        minecount = a;
    }

    void setstate(bool b) {
        state = b;
    }

    bool getmine() {
        return mined;
    }

    bool getstate() {
        return state;
    }

    bool getflag() {
        return flag;
    }

    void setflag(bool a) {
        flag = a;
    }
    
    cell *u;    
    cell *ul;    
    cell *ur;    
    cell *d;    
    cell *dl;    
    cell *dr;    
    cell *r;    
    cell *l;    
};

cell **game;
Fl_Window *win;
int r;
int c;
int mines;
int flags;
bool **helper;
void setgame(int r, int c, int mines);
void setbox(int r, int c);
void createwin(int r, int c, int mines);
void proximity(int r, int c);
void redo(int r, int c);
void cascade(int r, int c);
void cleargame(int r, int c, int mines);
Fl_Button *Easy;
Fl_Button *Med;
Fl_Button *Hard;
Fl_Button *newgame;
/*void revealm(int r, int c, int mines){
    Fl_Button* box = (Fl_Button*) widget;
    for (int i = 0; i < r + 2; i++) {
        for (int j = 0; j < c + 2; j++) {
            if(game[i][j].mined){
                box->color(FL_RED);
                box->box(FL_DOWN_BOX);
                box->labelfont(FL_BOLD);
                box->label("@");
            }                
        }
    }
}*/

void fl_message(const char);

void createwin(int r, int c, int mines) {
    win = new Fl_Window(c * 20, r * 20 + 50, "Minesearch");
    newgame = new Fl_Button ((c * 15) / 2 + 5, (r * 20 + 30), 40 , 15, "New");
   // Easy = new Fl_Button((c * 15) / 2 - 60, 0, 40, 15, "Easy");
   // Med = new Fl_Button((c * 15) / 2 - 20, 0, 40, 15, "Med");
   // Hard = new Fl_Button((c * 15) / 2 + 20, 0, 40, 15, "Hard");
}

void newg(Fl_Widget* widget, void*)
{
    cleargame(r, c, mines);
    setgame(r, c, mines);
    redo(r, c);
    proximity(r, c);
    cascade(r, c);
}

void Easyg(Fl_Widget* widget, void*)
{
    r = 10;
    c = 10;
    mines = 10;
    game = new cell*[r];            
    for (int i = 0; i < r; i++)
        game[i] = new cell[c];
    helper = new bool*[r];
    for (int i = 0; i < r + 2; i++)
        helper[i] = new bool[c];
    cleargame(r, c, mines);
    setgame(r, c, mines);
    redo(r, c);
    proximity(r, c);
    cascade(r, c);
}

void Medg(Fl_Widget* widget, void*)
{
    r = 20;
    c = 20;
    mines = 20;
    game = new cell*[r];            
    for (int i = 0; i < r; i++)
        game[i] = new cell[c];
    helper = new bool*[r];
    for (int i = 0; i < r + 2; i++)
        helper[i] = new bool[c];
    cleargame(r, c, mines);
    setgame(r, c, mines);
    redo(r, c);
    proximity(r, c);
    cascade(r, c);
}

void Hardg(Fl_Widget* widget, void*)
{
    r = 30;
    c = 30;
    mines = 30;
    game = new cell*[r];            
    for (int i = 0; i < r; i++)
        game[i] = new cell[c];
    helper = new bool*[r];
    for (int i = 0; i < r + 2; i++)
        helper[i] = new bool[c];
    cleargame(r, c, mines);
    setgame(r, c, mines);
    redo(r, c);
    proximity(r, c);
    cascade(r, c);
}

bool checkgame (int r, int c, int mines) {
    int check = 0;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (game[i][j].getflag() && helper[i][j] == true);
            check ++;
        }
    }
    if (check == mines)
        return true;
    else
        return false;
}

void setgame(int r, int c, int mines) {
    int counter = 0;
    for (int i = 0; i < r + 2; i++) {
        for (int j = 0; j < c + 2; j++) {
            helper[i][j] = false;
        }
    }
    while (counter < mines) {
        int x = rand() % r + 1;
        int y = rand() % c + 1;
        if (!helper[x][y]) {
            helper[x][y] = true;
            counter++;
        }
    }
}

void setcells(int r, int c) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            game[i][j] = cell(helper[i + 1][j + 1], (j * 20), (i * 20) + 30);
            game[i][j].box = new Fl_Button(game[i][j].x, game[i][j].y, 20, 20, " ");
            game[i][j].box->color(FL_GRAY);
            game[i][j].setflag(false);

            if (helper[i + 1][j + 1]) {
                game[i][j].mined = true;
            }
        }
    }
}

void redo(int r, int c) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (helper[i + 1][j + 1]) {
                game[i][j].mined = true;
            }
        }
    }
}

void cleargame(int r, int c, int mines){
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            game[i][j].mined = false;
            game[i][j].setstate(false);
            game[i][j].box->color(FL_GRAY);
            game[i][j].box->copy_label(" ");
            //game[i][j].box->box(FL_UP_BOX);
            game[i][j].box->color(FL_GRAY);
            game[i][j].setflag(false);
        }
    }
}

void handler(Fl_Widget* widget, void* mine) {
    cell* curr = (cell*) mine;
    int val = curr->num();
    if (Fl::event_button() == 1) {
        if (curr->getflag()) {
            if (!curr->getmine()) {
                curr->setflag(false);
                flags--;
            }
        }
        if (!curr->getmine()) {
            if (!curr->getstate()) {
                Fl_Button* box = (Fl_Button*) widget;
                if (val == 0){
                    box->color(FL_WHITE);
                    //box->box(FL_DOWN_BOX);
                    curr->setstate(true);
                    box->labelfont(FL_BOLD);
                    if(curr->u != NULL)
                        handler(curr->u->box, curr->u);
                    if(curr->ul != NULL)
                        handler(curr->ul->box, curr->ul);
                    if(curr->ur != NULL)
                        handler(curr->ur->box, curr->ur);
                    if(curr->r != NULL)
                        handler(curr->r->box, curr->r);
                    if(curr->l != NULL)
                        handler(curr->l->box, curr->l);
                    if(curr->d != NULL)
                        handler(curr->d->box, curr->d);
                    if(curr->dr != NULL)
                        handler(curr->dr->box, curr->dr);
                    if(curr->dl != NULL)
                        handler(curr->dl->box, curr->dl);
                }
                else{
                    string prox = to_string(val);
                    box->copy_label(prox.c_str());
                    if (val == 1)
                        box->color(FL_BLUE);
                    else if(val == 2)
                        box->color(FL_GREEN);
                    else if(val == 3)
                        box->color(FL_MAGENTA);
                    else if(val == 4)
                        box->color(FL_RED);
                    else
                        box->color(FL_WHITE);
                }                
            }
        } // add a if curr->getmine() to handle losses.
        if (curr->getmine()){
            fl_message("You're dead.");
            //revealm(r, c, mines);
            cleargame(r, c, mines);
            setgame(r, c, mines);
            redo(r, c);
            proximity(r, c);            
        } 
    }

    if (Fl::event_button() == 3) {
        curr = (cell*) mine;
        Fl_Button* box = (Fl_Button*) widget;
        if (curr->getflag()) {
            curr->setflag(false);
            flags--;
            box->copy_label("");
            box->labelcolor(FL_BLACK);
        } else {
            {
                curr->setflag(true);
                flags++;
                box->copy_label("!");
                box->labelcolor(FL_RED);
                box->labelfont(FL_BOLD);
               /* if (flags == mines){
                    checkgame(r, c, mines);
                    if (checkgame(r, c, mines))
                        fl_message("You Won!!");
                }*/
            }
        }
    }
    if (checkgame(r, c, mines == true)){
        fl_message("You won!");
    }
}

void proximity(int r, int c){
    for (int i = 1; i < r + 1; i++) {
        for (int j = 1; j < c + 1; j++) {
            int m = 0;
            if (helper[i + 1][j]){
                m++;
            }
            if (helper[i - 1][j]){
                m++;
            }
            if (helper[i][j + 1]){
                m++;
            }
            if (helper[i + 1][j + 1]){
                m++;
            }
            if (helper[i][j - 1]){
                m++;
            }
            if (helper[i - 1][j - 1]){
                m++;
            }
            if (helper[i + 1][j - 1]){
                m++;
            }
            if (helper[i - 1][j + 1]){
                m++;
            }
            game[i - 1][j - 1].setnum(m);
        }
    }
}

void cascade (int r, int c){
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            game[i][j].u = &game[i - 1][j];
            game[i][j].ul = &game[i - 1][j - 1];
            game[i][j].ur = &game[i - 1][j + 1];
            game[i][j].d = &game[i + 1][j];
            game[i][j].dl = &game[i + 1][j - 1];
            game[i][j].dr = &game[i + 1][j + 1];
            game[i][j].r = &game[i][j + 1];
            game[i][j].l = &game[i][j - 1];
            
            if(i == 0){
                game[i][j].u = NULL;
                game[i][j].ul = NULL;
                game[i][j].ur = NULL;
            }
            if(j == 0){
                game[i][j].l = NULL;
                game[i][j].ul = NULL;
                game[i][j].dl= NULL;
            }
            if(j == c - 1){
                game[i][j].r = NULL;
                game[i][j].ur = NULL;
                game[i][j].dr= NULL;
            }
            if(i == r - 1){
                game[i][j].d = NULL;
                game[i][j].dl = NULL;
                game[i][j].dr= NULL;
            }
        }
    }    
}

void Callb(int r, int c) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            game[i][j].box->callback(handler, &game[i][j]);
        }
    }
}

int main() {
    srand((unsigned) time(0));
    r = 10;
    c = 10;
    mines = 10;
    flags = 10;                     //consider adding in_box to make custom games
    game = new cell*[r];            //or add menus to have different difficulties.
    for (int i = 0; i < r; i++)
        game[i] = new cell[c];
    helper = new bool*[r];
    for (int i = 0; i < r + 2; i++)
        helper[i] = new bool[c];
    createwin(r, c, mines);
    //Easy->callback(Easyg);
   //Med->callback(Medg);
   // Hard->callback(Hardg);
    newgame->callback(newg);
    setgame(r, c, mines);
    setcells(r, c);
    proximity(r, c);
    cascade(r, c);
    Callb(r, c);
    win->end();
    win->show();
    return (Fl::run());
}
package com.example.damian.game15.logic;

/**
 * Created by Admin on 26.10.2015.
 */
public class GameCell {
    int id; //determines cell number, 0 for empty, 1..size^2-1 for others
    int x; //coordinates
    int y;
    boolean movable;

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isMovable() {

        return movable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {

        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameCell(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        movable = false;
    }
}

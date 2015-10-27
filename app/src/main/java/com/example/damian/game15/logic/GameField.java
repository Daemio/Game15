package com.example.damian.game15.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 26.10.2015.
 */
public class GameField {
    int size;

    public int getSize() {
        return size;
    }

    GameCell cells[][];

    public GameField(int size) { //inits winnary state
        this.size = size;
        cells = new GameCell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new GameCell(i * size + j + 1, i + 1, j + 1);
            }
        }
        cells[size - 1][size - 1].setId(0);//empty means cell id is 0
        //modifyMovableStates();
        cells[size - 2][size - 1].setMovable(true);
        cells[size - 1][size - 2].setMovable(true);
    }

    public GameCell getCellAt(int x, int y) {
        if (x > size || y > size || x < 1 || y < 1)
            return null;
        return cells[x - 1][y - 1];
    }

    public GameCell getCellById(int id) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].getId() == id) {
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    public void newGame(int difficulyty){
        ArrayList<GameCell> possibleMoves = new ArrayList<GameCell>();
        GameCell cur;
        int range;
        int randomValue;
        possibleMoves.clear();
        possibleMoves.addAll(getPossibleMoves());
        cur = possibleMoves.get((int)(Math.random()*2));
        moveCell(cur);
        for(int i=1;i<difficulyty;i++){
            possibleMoves.clear();
            possibleMoves.addAll(getPossibleMoves());
            possibleMoves.remove(cur);
            range = possibleMoves.size();
            randomValue = (int)(Math.random()*range);
            cur = possibleMoves.get(randomValue);
            moveCell(cur);
        }
    }

    private List<GameCell> getPossibleMoves(){
        return getNeighbours(getCellById(0));
    }

    private List<GameCell> getNeighbours(GameCell cell) {
        List<GameCell> list = new ArrayList<GameCell>();
        int x = cell.getX();
        int y = cell.getY();
        if (x > 1)
            list.add(this.getCellAt(x - 1, y));
        if (x < size)
            list.add(this.getCellAt(x + 1, y));
        if (y > 1)
            list.add(this.getCellAt(x, y - 1));
        if (y < size)
            list.add(this.getCellAt(x, y + 1));
        //Log.d("mytag", "" + list.size());
        return list;
    }

    private void modifyMovableStates() {
        for (GameCell cell : this.getNeighbours(getCellById(0))) {
            cell.setMovable(true);
        }
    }

    public boolean isWinnary() {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= size; j++) {
                if (getCellAt(i, j).getId() != ((i - 1) * size + j)) {
                    return false;
                }
            }
        }
        for (int i = 1;i<size;i++){
            if(getCellAt(size,i).getId() != (size-1)*size +i){
                return false;
            }
        }
        return true;
    }

    public boolean moveCell(GameCell cell) { //returns true only is move is performed
        if (!cell.isMovable())
            return false;
        for (GameCell temp : getNeighbours(getCellById(0))) {
            temp.setMovable(false);
        }
        GameCell zeroCell = getCellById(0);
        int x0 = zeroCell.getX();
        int y0 = zeroCell.getY();
        int x = cell.getX();
        int y = cell.getY();
        zeroCell.setX(x);
        zeroCell.setY(y);
        cell.setX(x0);
        cell.setY(y0);
        cells[x - 1][y - 1] = zeroCell;
        cells[x0 - 1][y0 - 1] = cell;
        modifyMovableStates();
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append("x").append("" + cells[i][j].getId());
            }
        }

        return s.toString();
    }
}

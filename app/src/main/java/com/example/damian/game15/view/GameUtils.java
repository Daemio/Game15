package com.example.damian.game15.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.damian.game15.TheApplication;
import com.example.damian.game15.events.CallBackMovePerformed;
import com.example.damian.game15.logic.GameField;

/**
 * Created by Admin on 26.10.2015.
 */
public class GameUtils {
    static GameField game;
    static SquareButton btn[][];
    public static CallBackMovePerformed callback = new CallBackMovePerformed(){
        @Override
        public void perform(int id) {
            if(game.moveCell(game.getCellById(id))){
                refreshUI();
            }
            if(game.isWinnary()) {
                Toast.makeText(TheApplication.getInstance().getApplicationContext(), "You Win!", Toast.LENGTH_LONG).show();
                int size = game.getSize();
                game.getCellAt(size - 1, size).setMovable(false);
                game.getCellAt(size, size - 1).setMovable(false);
                refreshUI();
            }
        }
    };
    public static void initField(LinearLayout llMain, int size){
        game = new GameField(size);
        btn = new SquareButton[size][size];
        int match = LinearLayout.LayoutParams.MATCH_PARENT;
        int wrap = LinearLayout.LayoutParams.WRAP_CONTENT;
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                match, wrap);
        rowParams.weight = 1;
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                0, wrap);
        btnParams.weight = 1;
        LinearLayout rows[] = new LinearLayout[size];
        for (int i = 0; i < size; i++) {
            rows[i] = new LinearLayout(llMain.getContext());
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < size; j++) {
                btn[i][j] = new SquareButton(llMain.getContext());
                btn[i][j].setId(i * size + j + 1);
                btn[i][j].setTextSize(40);
                //btn[i][j].setText(""+(i*size+j+1));
                rows[i].addView(btn[i][j], btnParams);
            }
            llMain.addView(rows[i], rowParams);
        }
        refreshUI();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                callback.perform(Integer.parseInt(((SquareButton) v).getText().toString()));
            }
        };
        for(int i=0;i<size;i++){
            for(int j = 0;j<size;j++){
                btn[i][j].setOnClickListener(listener);
            }
        }
    }

    public static void refreshUI(){
        int size = game.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = game.getCellAt(i+1,j+1).getId();
                btn[i][j].setText((value == 0 ? "":""+value));
                btn[i][j].setEnabled(game.getCellAt(i+1,j+1).isMovable());
            }
        }
    }

    public static void newGame(int difficulity){
        game.newGame(difficulity);
        refreshUI();
    }



}

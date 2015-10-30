package com.example.damian.game15.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damian.game15.TheApplication;
import com.example.damian.game15.events.CallBackMovePerformed;
import com.example.damian.game15.logic.GameField;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Admin on 26.10.2015.
 */
public class NotifyViews {
    final String SAVED_GAME_FILENAME = "game";
    final String SAVED_TIME = "time";
    final String SAVED_MOVES = "moves";
    final String SAVED_REQUIRED_MOVES = "reqMoves";

    GameField game;
    LinearLayout llMain;
    SquareButton btn[][];
    public int requiredMoves;
    public int countMoves;
    public int time; //time in seconds
    TextView tvCountMoves;
    TextView tvMinMoves;

    public void setLlMain(LinearLayout llMain) {
        this.llMain = llMain;
    }

    public void setTvMinMoves(TextView tvMinMoves) {
        this.tvMinMoves = tvMinMoves;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }

    public TextView tvTime;

    public void setCountMoves(TextView tvCountSteps) {
        this.tvCountMoves = tvCountSteps;
    }

    CallBackMovePerformed callback = new CallBackMovePerformed() {
        @Override
        public void perform(int id) {
            if (game.moveCell(game.getCellById(id))) {
                refreshUI();
            }
            if (game.isWinnary()) {
                Toast.makeText(TheApplication.getInstance().getApplicationContext(), "You Win!", Toast.LENGTH_LONG).show();
                int size = game.getSize();
                game.getCellAt(size - 1, size).setMovable(false);
                game.getCellAt(size, size - 1).setMovable(false);
                refreshUI();
            }
        }
    };

    public void initField(int size) {
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
                countMoves++;
                int viewId = v.getId();
                callback.perform(Integer.parseInt(((SquareButton) v).getText().toString()));
                if (tvCountMoves != null) {
                    tvCountMoves.setText("Moves: " + countMoves);
                }
            }
        };
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                btn[i][j].setOnClickListener(listener);
            }
        }
    }

    void refreshUI() {
        int size = game.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = game.getCellAt(i + 1, j + 1).getId();
                btn[i][j].setText((value == 0 ? "" : "" + value));
                btn[i][j].setEnabled(game.getCellAt(i + 1, j + 1).isMovable());
            }
        }
    }

    public void newGame(int difficulity) {
        game.newGame(difficulity);
        countMoves = 0;
        requiredMoves = difficulity;
        tvCountMoves.setText("Moves: 0");
        tvTime.setText("Time: 0 seconds");
        time = 0;
        tvMinMoves.setText("Required Moves: " + difficulity);
        refreshUI();
    }

    public void saveGame() throws IOException {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TheApplication.getInstance().getApplicationContext());
        preferences.edit().putInt(SAVED_MOVES, countMoves).apply();
        preferences.edit().putInt(SAVED_TIME, time).apply();
        preferences.edit().putInt(SAVED_REQUIRED_MOVES, requiredMoves).apply();
        FileOutputStream fos = TheApplication.getInstance().getApplicationContext().openFileOutput(SAVED_GAME_FILENAME, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(game);
        os.close();
        fos.close();
    }

    public void resumeGame() throws IOException, ClassNotFoundException {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TheApplication.getInstance().getApplicationContext());
        if (!preferences.contains(SAVED_TIME)) {
            return;
        }
        countMoves = preferences.getInt(SAVED_MOVES, 0);
        time = preferences.getInt(SAVED_TIME, 0);
        requiredMoves = preferences.getInt(SAVED_REQUIRED_MOVES, 0);

        FileInputStream fis = TheApplication.getInstance().getApplicationContext().openFileInput(SAVED_GAME_FILENAME);
        ObjectInputStream is = new ObjectInputStream(fis);
        game = (GameField) is.readObject();
        initField(game.getSize());
        tvCountMoves.setText("Moves: " + countMoves);
        tvTime.setText("Time: " + time + " seconds");
        tvMinMoves.setText("Required Moves: " + requiredMoves);
        is.close();
        fis.close();
        refreshUI();

    }
}

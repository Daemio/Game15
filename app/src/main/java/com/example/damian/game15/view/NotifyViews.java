package com.example.damian.game15.view;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damian.game15.TheApplication;
import com.example.damian.game15.Utils;
import com.example.damian.game15.events.CallBackMovePerformed;
import com.example.damian.game15.events.CallBackWinDialog;
import com.example.damian.game15.logic.GameField;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.view.dialogs.WinDialog;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 26.10.2015.
 */
public class NotifyViews {

    Handler uiHandler;
    GameField game;
    LinearLayout llMain;
    SquareButton btn[][];
    public int requiredMoves;
    public int countMoves;
    public int time; //time in seconds
    TextView tvCountMoves;
    TextView tvMinMoves;
    CallBackWinDialog callBackWinDialog;

    Timer myTimer = new Timer();
    TimerTask timerTask;

    public void setUiHandler(Handler uiHandler) {
        this.uiHandler = uiHandler;
    }

    public void setCallBackWinDialog(CallBackWinDialog callBackWinDialog) {
        this.callBackWinDialog = callBackWinDialog;
    }

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
//                Toast.makeText(TheApplication.getInstance().getApplicationContext(), "You Win!", Toast.LENGTH_LONG).show();
//                int size = game.getSize();
//                game.getCellAt(size - 1, size).setMovable(false);
//                game.getCellAt(size, size - 1).setMovable(false);
//                refreshUI();
                stopTimer();
                //GameSaver.deleteSavedGame();
                if(callBackWinDialog != null){
                    callBackWinDialog.onWin(time,countMoves,requiredMoves);
                }

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
        tvMinMoves.setText("Required Moves: " + requiredMoves);
        refreshUI();
        startTimer();

    }

    public void saveGame() throws IOException {
        stopTimer();
        if(game.isWinnary()){
            GameSaver.deleteSavedGame();
            return;
        }
        GameSaver.saveGame(game);
        GameSaver.saveIntValue(Utils.SAVED_TIME, time);
        GameSaver.saveIntValue(Utils.SAVED_MOVES, countMoves);
        GameSaver.saveIntValue(Utils.SAVED_REQUIRED_MOVES, requiredMoves);

    }

    public void resumeGame() throws IOException, ClassNotFoundException {
        game = GameSaver.getSavedGame();
        time = GameSaver.getIntValue(Utils.SAVED_TIME);
        countMoves = GameSaver.getIntValue(Utils.SAVED_MOVES);
        requiredMoves = GameSaver.getIntValue(Utils.SAVED_REQUIRED_MOVES);
        tvMinMoves.setText("Required Moves: " + requiredMoves);
        tvCountMoves.setText("Moves: " + countMoves);
        tvTime.setText("Time: " + time + " seconds");
        refreshUI();
        startTimer();
    }

    void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        tvTime.setText("Time: " + time + " seconds");
                    }
                });
            }
        };
        myTimer.schedule(timerTask, 0, 1000);
    }

    void stopTimer(){
        timerTask.cancel();
    }


}

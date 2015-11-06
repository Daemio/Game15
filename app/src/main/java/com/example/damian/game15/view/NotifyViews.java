package com.example.damian.game15.view;

import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
import com.example.damian.game15.events.CallBackMovePerformed;
import com.example.damian.game15.events.CallBackViewSwiped;
import com.example.damian.game15.events.CallBackWinDialog;
import com.example.damian.game15.logic.GameField;
import com.example.damian.game15.storage.GameSaver;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 26.10.2015.
 */
public class NotifyViews {

    private Handler uiHandler;
    private GameField game;
    private LinearLayout llMain;
    private SquareButton btn[][];
    private int requiredMoves;
    private int countMoves;
    private int time; //time in seconds

    private TextView tvCountMoves;
    private TextView tvMinMoves;
    private TextView tvTime;

    private CallBackWinDialog callBackWinDialog;
    private Timer myTimer = new Timer();
    private TimerTask timerTask;

    private CallBackMovePerformed callBackMovePerformed = new CallBackMovePerformed() {
        @Override
        public void perform(int id) {
            if (game.moveCell(game.getCellById(id))) {
                refreshUI();
            }
            if (game.isWinnary()) {
                stopTimer();
                if (callBackWinDialog != null) {
                    callBackWinDialog.onWin(time, countMoves, requiredMoves);
                }

            }
        }
    };



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

    public void setCountMoves(TextView tvCountSteps) {
        this.tvCountMoves = tvCountSteps;
    }

    public void initField(int size) {
        game = new GameField(size);
        createButtons(size);
        refreshUI();
        MyOnSwipeListener onSwipeListener = createListener();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                btn[i][j].setOnTouchListener(onSwipeListener);
            }
        }
    }

    private void refreshUI() {
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
        //startTimer();

    }

    public void saveGame() {
        //stopTimer();
        if (game.isWinnary()) {
            GameSaver.deleteSavedGame();
            return;
        }
        GameSaver.saveGame(game);
        GameSaver.saveIntValue(Utils.SAVED_TIME, time);
        GameSaver.saveIntValue(Utils.SAVED_MOVES, countMoves);
        GameSaver.saveIntValue(Utils.SAVED_REQUIRED_MOVES, requiredMoves);

    }

    public void resumeGame() {
        game = GameSaver.getSavedGame();
        time = GameSaver.getIntValue(Utils.SAVED_TIME);
        countMoves = GameSaver.getIntValue(Utils.SAVED_MOVES);
        requiredMoves = GameSaver.getIntValue(Utils.SAVED_REQUIRED_MOVES);
        tvMinMoves.setText("Required Moves: " + requiredMoves);
        tvCountMoves.setText("Moves: " + countMoves);
        tvTime.setText("Time: " + time + " seconds");
        refreshUI();
        //startTimer();
    }

    public void startTimer() {
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

    public void stopTimer() {
        timerTask.cancel();
    }

    private void createButtons(int size) {
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
                btn[i][j].setTextColor(Color.parseColor("#210B61"));
                btn[i][j].setBackgroundResource(R.drawable.game_button);
                rows[i].addView(btn[i][j], btnParams);
                btn[i][j].setGravity(Gravity.CENTER);
            }
            llMain.addView(rows[i], rowParams);
        }
    }

    private void performButtonSwipe(int btnId) {
        if(callBackMovePerformed == null){
            return;
        }
        countMoves++;
        tvCountMoves.setText("Moves: " + countMoves);
        callBackMovePerformed.perform(btnId);
    }

    private MyOnSwipeListener createListener(){
        return new MyOnSwipeListener(new CallBackViewSwiped() {
            @Override
            public void onRightSwipe(View v) {
                //Toast.makeText(TheApplication.getInstance().getApplicationContext(), "RIGHT", Toast.LENGTH_SHORT).show();
                int btnId = Integer.parseInt(((SquareButton) v).getText().toString());
                if (game.getMovementDirection(btnId) == Utils.DIRECTION_RIGHT) {
                    performButtonSwipe(btnId);
                }
            }

            @Override
            public void onLeftSwipe(View v) {
                //Toast.makeText(TheApplication.getInstance().getApplicationContext(), "LEFT", Toast.LENGTH_SHORT).show();
                int btnId = Integer.parseInt(((SquareButton) v).getText().toString());
                if (game.getMovementDirection(btnId) == Utils.DIRECTION_LEFT) {
                    performButtonSwipe(btnId);
                }
            }

            @Override
            public void onTopSwipe(View v) {
                //Toast.makeText(TheApplication.getInstance().getApplicationContext(), "TOP", Toast.LENGTH_SHORT).show();
                int btnId = Integer.parseInt(((SquareButton) v).getText().toString());
                if (game.getMovementDirection(btnId) == Utils.DIRECTION_TOP) {
                    performButtonSwipe(btnId);
                }
            }

            @Override
            public void onBottomSwipe(View v) {
                //Toast.makeText(TheApplication.getInstance().getApplicationContext(), "BOTTOM", Toast.LENGTH_SHORT).show();
                int btnId = Integer.parseInt(((SquareButton) v).getText().toString());
                if (game.getMovementDirection(btnId) == Utils.DIRECTION_BOTTOM) {
                    performButtonSwipe(btnId);
                }
            }
        });
    }

}



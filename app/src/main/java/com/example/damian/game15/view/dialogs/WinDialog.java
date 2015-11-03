package com.example.damian.game15.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
import com.example.damian.game15.events.CallBackDialogButton;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.view.MainActivity;
import com.example.damian.game15.view.fragments.FirstFragment;

/**
 * Created by Admin on 02.11.2015.
 */
public class WinDialog extends Dialog {
    TextView tvStats;
    TextView tvHighScore;
    Button btnOK;
    int time;
    int moves;
    int reqMoves;
    CallBackDialogButton callBackDialogButton;

    public void setCallBackDialogButton(CallBackDialogButton callBackDialogButton) {
        this.callBackDialogButton = callBackDialogButton;
    }

    public WinDialog(Context context,int time,int moves,int reqMoves) {
        super(context);
        this.time = time;
        this.moves = moves;
        this.reqMoves = reqMoves;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_win);
        tvHighScore = (TextView) findViewById(R.id.tvHighScoreSet);
        tvStats = (TextView) findViewById(R.id.tvStats);
        StringBuilder str = new StringBuilder("");
        str.append("Time: ").append("" + time + "\nMoves: ").append(""+moves+"\n");
        str.append("RequiredMoves: ").append(reqMoves);
        tvStats.setText(str.toString());
        int hsTimeTime,hsTimeMoves,hsMovesTime,hsMovesMoves;
        hsTimeTime = GameSaver.getIntValue(Utils.HIGHSCORE_BEST_TIME_TIME);
//        hsTimeMoves = GameSaver.getIntValue(Utils.HIGHSCORE_BEST_TIME_MOVES);
//        hsMovesTime = GameSaver.getIntValue(Utils.HIGHSCORE_MIN_MOVES_TIME);
        hsMovesMoves = GameSaver.getIntValue(Utils.HIGHSCORE_MIN_MOVES_MOVES);

        if(hsTimeTime == 0){//no games played before
            tvHighScore.setText("You set high score by time and moves!");
            GameSaver.saveIntValue(Utils.HIGHSCORE_BEST_TIME_TIME,time);
            GameSaver.saveIntValue(Utils.HIGHSCORE_BEST_TIME_MOVES,moves);
            GameSaver.saveIntValue(Utils.HIGHSCORE_MIN_MOVES_TIME,time);
            GameSaver.saveIntValue(Utils.HIGHSCORE_MIN_MOVES_MOVES,moves);
        }
        if(hsMovesMoves>moves){
            if(hsTimeTime>time){ //both
                tvHighScore.setText("You set high score by time and moves!");
                GameSaver.saveIntValue(Utils.HIGHSCORE_BEST_TIME_TIME,time);
                GameSaver.saveIntValue(Utils.HIGHSCORE_BEST_TIME_MOVES,moves);
                GameSaver.saveIntValue(Utils.HIGHSCORE_MIN_MOVES_TIME,time);
                GameSaver.saveIntValue(Utils.HIGHSCORE_MIN_MOVES_MOVES, moves);
            }else{ //by moves
                tvHighScore.setText("You set high score by moves!");
                GameSaver.saveIntValue(Utils.HIGHSCORE_MIN_MOVES_TIME,time);
                GameSaver.saveIntValue(Utils.HIGHSCORE_MIN_MOVES_MOVES, moves);
            }
        }else {
            if (hsTimeTime > time) {//by time
                tvHighScore.setText("You set high score by time!");
                GameSaver.saveIntValue(Utils.HIGHSCORE_BEST_TIME_TIME, time);
                GameSaver.saveIntValue(Utils.HIGHSCORE_BEST_TIME_MOVES, moves);
            }
        }

        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBackDialogButton!=null){
                    callBackDialogButton.onButtonPressed();
                }
            }
        });
    }
}

package com.example.damian.game15.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.damian.game15.R;

public class MainActivity extends AppCompatActivity {
    LinearLayout llMain;
    TextView tvMoves;
    TextView tvMinimumMoves;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_fragment);
        llMain = (LinearLayout) findViewById(R.id.llField);
        tvMoves = (TextView) findViewById(R.id.tvMoves);
        tvMinimumMoves = (TextView) findViewById(R.id.tvMinimumMoves);
        tvTime = (TextView) findViewById(R.id.tvTime);
        NotifyViews views = new NotifyViews();
        views.setCountSteps(tvMoves);
        views.initField(llMain, 4);
        views.newGame(20);
    }


}

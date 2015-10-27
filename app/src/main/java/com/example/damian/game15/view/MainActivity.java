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
    RadioGroup rgDifficulity;
    TextView tvMoves;
    TextView tvTime;
    Button btnNew;
    Button btnSave;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llMain = (LinearLayout) findViewById(R.id.llField);
        rgDifficulity = (RadioGroup) findViewById(R.id.rgDifficulity);
        tvMoves = (TextView) findViewById(R.id.tvMoves);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnNew = (Button) findViewById(R.id.btnNewGame);
        btnSave = (Button) findViewById(R.id.btnSaveGame);
        btnLoad = (Button) findViewById(R.id.btnLoadGame);
        NotifyViews views = new NotifyViews();
        views.setCountSteps(tvMoves);
        views.initField(llMain, 4);
        views.newGame(15);
    }


}

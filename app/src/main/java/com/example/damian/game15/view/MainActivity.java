package com.example.damian.game15.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.damian.game15.R;
import com.example.damian.game15.logic.GameField;

public class MainActivity extends AppCompatActivity {
    LinearLayout llMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llMain = (LinearLayout) findViewById(R.id.llField);
        GameUtils.initField(llMain,4);
        GameUtils.newGame(7);

    }

}

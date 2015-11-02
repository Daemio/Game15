package com.example.damian.game15.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.view.MainActivity;
import com.example.damian.game15.view.NotifyViews;

import java.io.IOException;

/**
 * Created by Admin on 29.10.2015.
 */
public class GameFragment extends Fragment {
    LinearLayout llMain;
    TextView tvMoves;
    TextView tvMinimumMoves;
    TextView tvTime;
    Button btnBackToMenu;
    int mode;
    NotifyViews views;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = this.getArguments().getInt(Utils.BUNDLE_KEY_MODE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_fragment, container, false);
        llMain = (LinearLayout) v.findViewById(R.id.llField);
        tvMoves = (TextView) v.findViewById(R.id.tvMoves);
        tvMinimumMoves = (TextView) v.findViewById(R.id.tvMinimumMoves);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
        btnBackToMenu = (Button) v.findViewById(R.id.btnBackToMenu);
        views = new NotifyViews();
        views.setCountMoves(tvMoves);
        views.setTvMinMoves(tvMinimumMoves);
        views.setTvTime(tvTime);
        views.setLlMain(llMain);
        views.initField(4);

        if (mode == Utils.MODE_NEW) { //if we start new game
            int difficulty = GameSaver.getIntValue(Utils.DIFFICULTY);
            if (difficulty == 0) {
                difficulty = Utils.DIFFICULTY_EASY;
            }
            views.newGame(difficulty);
        } else { //if we resume saved game
            try {
                try {
                    views.resumeGame();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    views.saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((MainActivity) getActivity()).getTransitManager().back();
            }
        });
        return v;
    }
}

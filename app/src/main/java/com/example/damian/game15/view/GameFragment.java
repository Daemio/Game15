package com.example.damian.game15.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.damian.game15.R;

/**
 * Created by Admin on 29.10.2015.
 */
public class GameFragment extends Fragment {
    LinearLayout llMain;
    TextView tvMoves;
    TextView tvMinimumMoves;
    TextView tvTime;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_fragment,container,false);
        llMain = (LinearLayout) v.findViewById(R.id.llField);
        tvMoves = (TextView) v.findViewById(R.id.tvMoves);
        tvMinimumMoves = (TextView) v.findViewById(R.id.tvMinimumMoves);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
        NotifyViews views = new NotifyViews();
        views.setCountMoves(tvMoves);
        views.setTvMinMoves(tvMinimumMoves);
        views.setTvTime(tvTime);
        views.setLlMain(llMain);
        views.initField(4);
        views.newGame(20);

        return v;
    }
}

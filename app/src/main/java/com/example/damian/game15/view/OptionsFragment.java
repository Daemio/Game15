package com.example.damian.game15.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.damian.game15.R;

/**
 * Created by Admin on 29.10.2015.
 */
public class OptionsFragment extends Fragment {
    RadioGroup rgDifficulity;
    TextView tvHighscoreBestTime;
    TextView tvHighscoreMinMoves;
    Button btnCancel;
    Button btnSave;
    Button btnReset;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.option_fragment,container,false);
        rgDifficulity = (RadioGroup) v.findViewById(R.id.rgDifficulity);
        tvHighscoreBestTime = (TextView) v.findViewById(R.id.tvHighscoreBestTime);
        tvHighscoreMinMoves = (TextView) v.findViewById(R.id.tvHighscoreMinMoves);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnReset = (Button) v.findViewById(R.id.btnReset);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnCancel:
                        break;
                    case R.id.btnSave:
                        break;
                    case R.id.btnReset:
                        break;
                }
            }
        };
        btnCancel.setOnClickListener(btnListener);
        btnSave.setOnClickListener(btnListener);
        btnReset.setOnClickListener(btnListener);

        return v;
    }
}

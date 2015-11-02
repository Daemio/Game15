package com.example.damian.game15.view.fragments;

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
import com.example.damian.game15.Utils;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.view.MainActivity;

/**
 * Created by Admin on 29.10.2015.
 */
public class OptionsFragment extends Fragment {
    RadioGroup rgDifficulty;
    TextView tvHighScoreBestTime;
    TextView tvHighScoreMinMoves;
    Button btnCancel;
    Button btnSave;
    Button btnReset;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.option_fragment,container,false);
        rgDifficulty = (RadioGroup) v.findViewById(R.id.rgDifficulity);
        tvHighScoreBestTime = (TextView) v.findViewById(R.id.tvHighscoreBestTime);
        tvHighScoreMinMoves = (TextView) v.findViewById(R.id.tvHighscoreMinMoves);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnReset = (Button) v.findViewById(R.id.btnReset);


        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnCancel:
                        ((MainActivity)getActivity()).getTransitManager().back();
                        break;
                    case R.id.btnSave:
                        int difficulty;
                        switch (rgDifficulty.getCheckedRadioButtonId()){
                            case R.id.rbEasy:
                                difficulty = Utils.DIFFICULTY_EASY;
                                break;
                            case R.id.rbMedium:
                                difficulty = Utils.DIFFICULTY_MEDIUM;
                                break;
                            case R.id.rbHard:
                                difficulty = Utils.DIFFICULTY_HARD;
                                break;
                            default:
                                difficulty = Utils.DIFFICULTY_EASY;
                                break;
                        }
                        GameSaver.saveIntValue(Utils.DIFFICULTY,difficulty);
                        break;
                    case R.id.btnReset:
                        tvHighScoreBestTime.setText("");
                        tvHighScoreMinMoves.setText("");
                        rgDifficulty.check(R.id.rbEasy);
                        GameSaver.saveIntValue(Utils.DIFFICULTY,Utils.DIFFICULTY_EASY);
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

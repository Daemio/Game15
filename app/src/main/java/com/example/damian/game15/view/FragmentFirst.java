package com.example.damian.game15.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.damian.game15.R;
import com.example.damian.game15.TheApplication;

/**
 * Created by Admin on 29.10.2015.
 */
public class FragmentFirst extends Fragment {
    Button btnResume;
    Button btnNew;
    Button btnOptions;
    Button btnExit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment,container,false);
        btnResume = (Button) v.findViewById(R.id.btnResumeGame);//fix enabled state
        btnNew = (Button) v.findViewById(R.id.btnNewGame);
        btnOptions = (Button) v.findViewById(R.id.btnOptions);
        btnExit = (Button) v.findViewById(R.id.btnExit);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnResumeGame:
                        break;
                    case R.id.btnNewGame:
                        break;
                    case R.id.btnOptions:
                        break;
                    case R.id.btnExit:
                        break;
                }
            }
        };
        btnResume.setOnClickListener(listener);
        btnNew.setOnClickListener(listener);
        btnOptions.setOnClickListener(listener);
        btnExit.setOnClickListener(listener);

        return v;
    }
}

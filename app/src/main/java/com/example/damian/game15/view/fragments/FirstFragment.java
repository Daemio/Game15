package com.example.damian.game15.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.view.MainActivity;

/**
 * Created by Admin on 29.10.2015.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{
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

        btnResume.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        btnOptions.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        btnResume.setEnabled(GameSaver.gameIsSaved());

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnResume.setEnabled(GameSaver.gameIsSaved());
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;
        Bundle bundle;
        switch (v.getId()){
            case R.id.btnResumeGame:
                fragment = new GameFragment();
                bundle = new Bundle();
                bundle.putInt(Utils.BUNDLE_KEY_MODE, Utils.MODE_RESUME);
                ((MainActivity) getActivity()).getTransitManager().switchFragment(fragment,bundle,true);
                break;
            case R.id.btnNewGame:
                fragment = new GameFragment();
                bundle = new Bundle();
                bundle.putInt(Utils.BUNDLE_KEY_MODE, Utils.MODE_NEW);
                ((MainActivity) getActivity()).getTransitManager().switchFragment(fragment,bundle,true);
                break;
            case R.id.btnOptions:
                fragment = new OptionsFragment();
                ((MainActivity)getActivity()).getTransitManager().switchFragment(fragment,null,true);
                break;
            case R.id.btnExit:
                ((MainActivity)getActivity()).showExitDialog();
                break;
        }
    }
}

package com.example.damian.game15.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.damian.game15.R;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.transit.TransitManager;
import com.example.damian.game15.view.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {

    private TransitManager manager;


    public TransitManager getTransitManager() {
        return manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new TransitManager(this, R.id.flFragmentContainer);
        GameSaver.setActivity(this);
        initStartFragment();


        //CountDownTimer;
    }

    private void initStartFragment(){
        manager.switchFragment(new SplashFragment());
    }
}

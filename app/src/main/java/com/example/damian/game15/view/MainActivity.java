package com.example.damian.game15.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.damian.game15.R;
import com.example.damian.game15.transit.TransitManager;

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
        initStartFragment();
        //CountDownTimer;
    }

    private void initStartFragment(){
        manager.switchFragment(new SplashFragment());
    }
}

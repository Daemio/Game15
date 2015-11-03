package com.example.damian.game15.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import com.example.damian.game15.R;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.transit.TransitManager;
import com.example.damian.game15.view.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {
    final Handler handler = new Handler();

    public Handler getHandler() {
        return handler;
    }

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

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }

    private void initStartFragment(){
        manager.switchFragment(new SplashFragment());
    }
}

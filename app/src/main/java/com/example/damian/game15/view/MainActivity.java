package com.example.damian.game15.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
import com.example.damian.game15.events.CallBackDialogExit;
import com.example.damian.game15.storage.GameSaver;
import com.example.damian.game15.transit.TransitManager;
import com.example.damian.game15.view.dialogs.ExitDialog;
import com.example.damian.game15.view.fragments.FirstFragment;
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
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            showExitDialog();
        } else {
            getFragmentManager().popBackStack();
        }


        return;
    }

    private void initStartFragment(){
        manager.switchFragment(new SplashFragment());
    }

    public void showExitDialog(){
        final ExitDialog dialog = new ExitDialog(this);
        dialog.setCallback(new CallBackDialogExit() {
            @Override
            public void onExitDialog(int actionCode) {
                switch (actionCode){
                    case Utils.DIALOG_ACTION_CANCEL:
                        dialog.dismiss();
                        break;
                    case Utils.DIALOG_ACTION_CLOSE:
                        dialog.dismiss();
                        finish();
                        break;
                }
            }
        });
        dialog.show();
    }
}

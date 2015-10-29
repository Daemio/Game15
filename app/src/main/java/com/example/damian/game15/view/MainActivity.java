package com.example.damian.game15.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.damian.game15.R;

public class MainActivity extends AppCompatActivity {
    final int fragmentContainerId = R.id.flFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment gameFragment = new FragmentGame();
        getFragmentManager().beginTransaction().add(fragmentContainerId, gameFragment).commit();

    }


}

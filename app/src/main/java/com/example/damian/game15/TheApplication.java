package com.example.damian.game15;

import android.app.Application;

/**
 * Created by Admin on 26.10.2015.
 */
public class TheApplication extends Application {
    private static TheApplication instance;

    public static TheApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}

package com.example.damian.game15;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.example.damian.game15.view.MainActivity;

import java.util.prefs.Preferences;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void TestPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TheApplication.getInstance().getApplicationContext());
        int some = 10;
        int x = preferences.getInt("some",0);
        some++;
        Log.d("mytag", ""+x);
        preferences.edit().putInt("some", some).commit();
        x = preferences.getInt("some",x);
        Log.d("mytag", ""+x);
    }
}
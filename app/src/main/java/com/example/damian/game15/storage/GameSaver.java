package com.example.damian.game15.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.damian.game15.TheApplication;
import com.example.damian.game15.Utils;
import com.example.damian.game15.logic.GameField;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Admin on 02.11.2015.
 */
public class GameSaver {
    static Activity activity;

    public static void setActivity(Activity mainActivity) {
        activity = mainActivity;
    }

    public static void saveIntValue(String valueKey, int value) {
        SharedPreferences preferences = activity.getSharedPreferences(Utils.PREFERENCES_KEY, Context.MODE_PRIVATE);
        preferences.edit().putInt(valueKey, value).apply();
    }

    public static int getIntValue(String valueKey) {
        SharedPreferences preferences = activity.getSharedPreferences(Utils.PREFERENCES_KEY, Context.MODE_PRIVATE);
        return preferences.getInt(valueKey, 0);
    }

    public static void saveGame(GameField game) {
        activity.deleteFile(Utils.SAVED_GAME_FILENAME);
        try {
            FileOutputStream fos = activity.openFileOutput(Utils.SAVED_GAME_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(game);
            os.close();
            fos.close();
        }catch (IOException e){
            Toast.makeText(TheApplication.getInstance().getApplicationContext(),"Game can not be saved",Toast.LENGTH_LONG);
        }
        SharedPreferences preferences = activity.getSharedPreferences(Utils.PREFERENCES_KEY, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(Utils.GAME_IS_SAVED, true).apply();
    }

    public static GameField getSavedGame(){
        GameField game;
        try {
            FileInputStream fis = activity.openFileInput(Utils.SAVED_GAME_FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            game = (GameField) is.readObject();
            is.close();
            fis.close();
        }catch (Exception e){
            Toast.makeText(TheApplication.getInstance().getApplicationContext(),"Saved game can not be resumed, starting new",Toast.LENGTH_LONG);
            return new GameField(4);
        }
        return game;
    }

    public static void deleteSavedGame() {
        activity.deleteFile(Utils.SAVED_GAME_FILENAME);
        SharedPreferences preferences = activity.getSharedPreferences(Utils.PREFERENCES_KEY, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(Utils.GAME_IS_SAVED, false).apply();
    }

    public static boolean gameIsSaved() {
        SharedPreferences preferences = activity.getSharedPreferences(Utils.PREFERENCES_KEY, Context.MODE_PRIVATE);
        return preferences.getBoolean(Utils.GAME_IS_SAVED, false);
    }
}

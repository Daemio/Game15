package com.example.damian.game15.transit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Admin on 30.10.2015.
 */
public class TransitManager {

    private Activity activity;
    private int fragmentContainerRes;
    private FragmentManager fragmentManager;

    public TransitManager(Activity activity, int fragmentContainerRes) {
        this.activity = activity;
        this.fragmentContainerRes = fragmentContainerRes;
        fragmentManager= activity.getFragmentManager();
    }

    public void switchFragment(Fragment fragment){
        switchFragment(fragment, null, true);
    };

    public void switchFragment(Fragment fragment, Bundle bundle){
        switchFragment(fragment, bundle, true);
    };

    public void switchFragment(Fragment fragment, Bundle bundle, boolean addToStack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (bundle!=null){
            fragment.setArguments(bundle);
        }
        transaction.addToBackStack(addToStack ? "" : null);
        transaction.replace(fragmentContainerRes, fragment).commitAllowingStateLoss();
    };

    public void back(){
        fragmentManager.popBackStack();
    }
}

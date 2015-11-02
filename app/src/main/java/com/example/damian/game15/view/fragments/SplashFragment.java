package com.example.damian.game15.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.damian.game15.R;
import com.example.damian.game15.view.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 29.10.2015.
 */
public class SplashFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash,container,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        Picasso.with(v.getContext()).load(R.drawable.game_splash).into(imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)getActivity()).getTransitManager().switchFragment(new FirstFragment());
            }
        }, 2000);
        return v;
    }
}

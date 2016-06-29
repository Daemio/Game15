package com.example.damian.game15.view.fragments;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
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
        Picasso.with(v.getContext()).load(R.drawable.frame8).into(imageView);
        //AnimationDrawable anim = getAnimation();
        //v.setBackground(anim);//requires API 16+
        //anim.start();
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)getActivity()).getTransitManager().switchFragment(new FirstFragment());
            }
        }, 3000);
        


        return v;
    }

    private AnimationDrawable getAnimation() {

        BitmapDrawable frame0 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame0);
        BitmapDrawable frame1 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame1);
        BitmapDrawable frame2 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame2);
        BitmapDrawable frame3 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame3);
        BitmapDrawable frame4 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame4);
        BitmapDrawable frame5 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame5);
        BitmapDrawable frame6 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame6);
        BitmapDrawable frame7 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame7);
        BitmapDrawable frame8 = (BitmapDrawable) getResources().getDrawable(
                R.drawable.frame8);

        AnimationDrawable mAnimationDrawable = new AnimationDrawable();

        mAnimationDrawable.setOneShot(false);
        mAnimationDrawable.addFrame(frame0, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame1, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame2, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame3, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame4, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame5, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame6, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame7, Utils.SPLASH_ANIMATION_DURATION);
        mAnimationDrawable.addFrame(frame8, Utils.SPLASH_ANIMATION_DURATION);

        return mAnimationDrawable;

    }
}

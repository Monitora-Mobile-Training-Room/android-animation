package com.example.ricardosgobbe.revealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;

/**
 * Created by ricardo.sgobbe on 29/12/2015.
 */
public class FragmentReveal {
    private static View mView;
    private static int mX, mY, mMaxSize;
    private static int[] vector;
    private static MainActivity mContext;


    public FragmentReveal() {
    }


    public FragmentReveal(View view, int x, int y) {
        mView = view;
        vector = new int[2];
        mView.getLocationOnScreen(vector);
        mX = x - vector[0];
        mY = y - vector[1];
    }

    public void getMaxRadius(MainActivity context) {
        mContext = context;
        Point point = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(point);
        mMaxSize = point.y;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void startAnimation() {
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mView, mX, mY, 0, mMaxSize);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mContext.getFab().setVisibility(View.GONE);
                mView.setVisibility(View.VISIBLE);
            }
        });
        circularReveal.start();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void closeAnimation() {
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mView, mX, mY, mMaxSize, 0);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.setVisibility(View.GONE);
                mContext.getFab().setVisibility(View.VISIBLE);
            }
        });
        circularReveal.start();
    }
}

package com.example.ricardosgobbe.revealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ricardo.sgobbe on 28/12/2015.
 */
public class FragmentTest extends Fragment {

    private static FragmentTest mFrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.card_view, container, false);
        //view.setVisibility(View.GONE);
        TextView txt = (TextView) view.findViewById(R.id.txt_view);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReveal reveal = new FragmentReveal();
                reveal.closeAnimation();
            }
        });
        return view;
    }


    public static FragmentTest getInstance(){
        if(mFrag == null){
            mFrag = new FragmentTest();
        }
        return mFrag;
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }
}

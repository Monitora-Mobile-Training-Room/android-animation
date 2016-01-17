package com.example.ricardosgobbe.revealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private View view;
    private FragmentReveal fragmentReveal;
    private FragmentTest fragmentTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        view = findViewById(R.id.fragment_container);
        bindClick();
        defineCoordinates();
    }

    private void defineCoordinates() {
        int[] click = new int[2];
        fab.getLocationOnScreen(click);
        click[0] += fab.getWidth()/2;
        click[1] += fab.getHeight()/2;
        fragmentReveal = new FragmentReveal(view, click[0], click[1]);
        fragmentReveal.getMaxRadius(this);
    }

    private void bindClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, android.support.design.R.anim.abc_fade_out);
                fab.startAnimation(animation);
                prepareFragment();
                defineCoordinates();
                fragmentReveal.startAnimation();
            }
        });
    }

    private void prepareFragment() {
            fragmentTest = (FragmentTest) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if(fragmentTest == null){
                fragmentTest = FragmentTest.getInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentTest);
                transaction.addToBackStack(null);
                transaction.commit();
            }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public FloatingActionButton getFab() {
        return fab;
    }

    public View getView() {
        return view;
    }
}

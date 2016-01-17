package com.example.ricardosgobbe.revealanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by ricardo.sgobbe on 11/01/2016.
 */
public class SunsetActivity extends AppCompatActivity {

    private View mView;
    private View mSun;
    private View mSky;
    private ImageView mRay;
    private ImageView mSunShadow;
    private int mSkyColor;
    private int mSunsetColor;
    private int mNightColor;
    private AnimatorSet animatorSet;
    private AnimatorSet animatorSet2;
    private boolean flag;
    private float startYSun;
    private float endYSun;
    private View mSea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sunset);
        mSun = findViewById(R.id.all_sun);
        mSky = findViewById(R.id.sky);
        mView = findViewById(R.id.all_view);
        mRay = (ImageView) findViewById(R.id.ray_sun);
        mSunShadow = (ImageView) findViewById(R.id.sun_shadow);
        mSea = findViewById(R.id.sea);

        startRayPulse();

        Resources resources = getResources();
        mSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetColor = resources.getColor(R.color.sunset_sky);
        mNightColor = resources.getColor(R.color.night_sky);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    startSunRise();
                    flag = false;
                } else {
                    startAnimation();
                    flag = true;
                }
            }
        });
    }


    private void startAnimation() {
        startYSun = mSun.getTop();
        endYSun = mView.getHeight();

        ObjectAnimator animator = ObjectAnimator.ofFloat(mSun, "y", startYSun, endYSun).setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator animator2 = ObjectAnimator.ofInt(mSky, "backgroundColor", mSkyColor, mSunsetColor)
                .setDuration(2000);
        animator2.setEvaluator(new ArgbEvaluator());

        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSky, "backgroundColor", mSunsetColor, mNightColor)
                .setDuration(2000);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());

        float startYShadow = mSky.getHeight();
        float endYShadow  = mSunShadow.getTop();

        ObjectAnimator shadow = ObjectAnimator.ofFloat(mSunShadow, "y", startYShadow, endYShadow).setDuration(3000);
        shadow.setInterpolator(new AccelerateInterpolator());


        animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(animator2).before(nightSkyAnimator);
        this.animatorSet.play(animator)
                .with(animatorSet2);
        AnimatorSet withShadow = new AnimatorSet();
        withShadow.play(animatorSet).with(shadow);
        withShadow.start();


    }

    private void startSunRise() {

        ObjectAnimator animator = ObjectAnimator.ofFloat(mSun, "y", endYSun, startYSun).setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator animator2 = ObjectAnimator.ofInt(mSky, "backgroundColor", mSunsetColor, mSkyColor);
        animator2.setDuration(3000).setEvaluator(new ArgbEvaluator());

        ObjectAnimator animator3 = ObjectAnimator.ofInt(mSky, "backgroundColor", mNightColor, mSunsetColor);
        animator3.setDuration(2000).setEvaluator(new ArgbEvaluator());


        animatorSet2 = new AnimatorSet();
        AnimatorSet anin = new AnimatorSet();
        anin.play(animator3).before(animator2);

        this.animatorSet2.play(animator).with(anin);
        this.animatorSet2.start();
    }

    private void startRayPulse() {
        ValueAnimator valueAnimator = new ValueAnimator();
        final GradientDrawable drawable = (GradientDrawable) mRay.getDrawable();
        valueAnimator.setFloatValues(getResources().getDimension(R.dimen.sun_ray_full), getResources().getDimension(R.dimen.sun_ray_half));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float valor = (float) animation.getAnimatedValue();
                drawable.setGradientRadius(valor);
            }
        });
        valueAnimator.setDuration(250);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }
}

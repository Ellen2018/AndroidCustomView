package com.ellen.androidcustomview.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellen.androidcustomview.R;
import com.ellen.androidcustomview.anim.Rotate3DAnimator;

public class Rotate3DActivity extends AppCompatActivity {

    private Button bt;
    private ImageView iv1,iv2;
    private LinearLayout ll;

    private int duration = 600;
    private Rotate3DAnimator openAnimation;
    private Rotate3DAnimator closeAnimation;

    private boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_3d);

        bt = findViewById(R.id.bt);
        ll = findViewById(R.id.ll);
        iv1 = findViewById(R.id.iv_1);
        iv2 = findViewById(R.id.iv_2);

        initOpenAnim();
        initCloseAnim();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(openAnimation.hasStarted() && !openAnimation.hasEnded()){
                     return;
                 }
                if(closeAnimation.hasStarted() && !closeAnimation.hasEnded()){
                    return;
                }

                if(isOpen){
                    ll.startAnimation(closeAnimation);
                }else {
                    ll.startAnimation(openAnimation);
                }
                isOpen = !isOpen;

            }
        });
    }

    private void initOpenAnim(){
        openAnimation = new Rotate3DAnimator(0,90,true);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
        openAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                iv2.setVisibility(View.VISIBLE);
                iv1.setVisibility(View.GONE);

                Rotate3DAnimator rotate3DAnimator = new Rotate3DAnimator(90,180,false);
                rotate3DAnimator.setDuration(duration);
                rotate3DAnimator.setFillAfter(true);
                ll.startAnimation(rotate3DAnimator);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initCloseAnim(){
        closeAnimation = new Rotate3DAnimator(180,90,true);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                iv2.setVisibility(View.GONE);
                iv1.setVisibility(View.VISIBLE);

                Rotate3DAnimator rotate3DAnimator = new Rotate3DAnimator(90,0,false);
                rotate3DAnimator.setDuration(duration);
                rotate3DAnimator.setFillAfter(true);
                ll.startAnimation(rotate3DAnimator);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

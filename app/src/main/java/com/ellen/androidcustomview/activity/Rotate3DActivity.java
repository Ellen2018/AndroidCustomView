package com.ellen.androidcustomview.activity;

import android.os.Bundle;
import android.view.View;
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
    private ImageView iv;
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
        iv = findViewById(R.id.iv);

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
        openAnimation = new Rotate3DAnimator(0,180);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
    }

    private void initCloseAnim(){
        closeAnimation = new Rotate3DAnimator(180,0);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
    }
}

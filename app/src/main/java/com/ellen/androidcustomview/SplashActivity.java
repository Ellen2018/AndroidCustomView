package com.ellen.androidcustomview;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivBg = findViewById(R.id.iv);
        anim();
    }

    /**
     * Splash界面缩放效果
     */
    private void anim(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.2f,1.0f,1.2f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(6000);
        ivBg.startAnimation(scaleAnimation);
    }
}

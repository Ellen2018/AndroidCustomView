package com.ellen.androidcustomview.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellen.androidcustomview.R;
import com.ellen.customview.view.CameraImageView;

public class CameraActivity extends AppCompatActivity {

    private CameraImageView cameraImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraImageView = findViewById(R.id.camera_img_view);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,360);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                cameraImageView.setProgress(value);
                Log.e("Ellen2021","变化的值:"+value);
            }
        });
        valueAnimator.setDuration(10000);
        valueAnimator.start();
    }
}

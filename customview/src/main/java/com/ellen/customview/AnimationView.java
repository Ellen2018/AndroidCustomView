package com.ellen.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 演示自定义View的动画
 */
public class AnimationView extends View {
    public AnimationView(Context context) {
        super(context);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

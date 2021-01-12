package com.ellen.customview.interpolator;

import android.animation.TimeInterpolator;

/**
 * 自定义插值器
 */
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1-input;
    }
}

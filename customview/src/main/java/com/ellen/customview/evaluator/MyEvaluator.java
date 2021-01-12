package com.ellen.customview.evaluator;

import android.animation.TypeEvaluator;

/**
 * 自定义估值器（Integer）
 */
public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        return (int) (startValue + fraction * (endValue-startValue));
    }
}

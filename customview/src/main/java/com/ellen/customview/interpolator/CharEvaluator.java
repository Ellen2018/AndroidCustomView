package com.ellen.customview.interpolator;

import android.animation.TypeEvaluator;

/**
 * Char类型的估值器
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startIntValue = startValue;
        int endIntValue = endValue;
        int curInt = (int)(startIntValue + fraction * (endIntValue - startIntValue));
        char result = (char) curInt;
        return result;
    }
}

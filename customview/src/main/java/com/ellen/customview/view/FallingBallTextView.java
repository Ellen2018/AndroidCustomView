package com.ellen.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class FallingBallTextView extends TextView {
    public FallingBallTextView(Context context) {
        super(context);
    }

    public FallingBallTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FallingBallTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 此方法提供给ObjectAnimator使用
     * @param point
     */
    public void setFallingPos(Point point) {
        layout(point.x,point.y,point.x + getWidth(),point.y + getHeight());
    }
}

package com.ellen.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ellen.customview.R;

public class AutoView extends View {

    private int intValue;
    private String strValue;
    private float dpValue;
    private int age;
    private float floatValue;

    public AutoView(Context context) {
        super(context);
    }

    public AutoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //解析自定义属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoView);
        int n = typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.AutoView_intValue) {
                intValue = typedArray.getInt(attr,-1);
            } else if (attr == R.styleable.AutoView_floatValue) {
                floatValue = typedArray.getFloat(attr,-1);
            }else if(attr == R.styleable.AutoView_strValue){
                strValue = typedArray.getString(attr);
            }else if(attr == R.styleable.AutoView_dpValue){
                dpValue = typedArray.getDimension(attr,0);
            }else{
                age = typedArray.getInt(attr,-1);
            }
        }
        typedArray.recycle();
    }

    public AutoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

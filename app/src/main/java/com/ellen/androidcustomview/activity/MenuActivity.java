package com.ellen.androidcustomview.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellen.androidcustomview.R;

public class MenuActivity extends AppCompatActivity {

    private Button btMenu;
    private Button menu1,menu2,menu3,menu4,menu5;
    private boolean openMenu = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btMenu = findViewById(R.id.bt_menu);
        menu1 = findViewById(R.id.bt_menu_1);
        menu2 = findViewById(R.id.bt_menu_2);
        menu3 = findViewById(R.id.bt_menu_3);
        menu4 = findViewById(R.id.bt_menu_4);
        menu5 = findViewById(R.id.bt_menu_5);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(openMenu){
                    //关闭菜单
                    closeMenu();
                }else {
                    //打开菜单
                    openMenu();
                }
                openMenu = !openMenu;
            }
        });
    }

    private void openMenu(){
        doAnimatorOpen(menu1,0,5,300);
        doAnimatorOpen(menu2,1,5,300);
        doAnimatorOpen(menu3,2,5,300);
        doAnimatorOpen(menu4,3,5,300);
        doAnimatorOpen(menu5,4,5,300);
    }

    private void closeMenu(){
        doAnimatorClose(menu1,0,5,300);
        doAnimatorClose(menu2,1,5,300);
        doAnimatorClose(menu3,2,5,300);
        doAnimatorClose(menu4,3,5,300);
        doAnimatorClose(menu5,4,5,300);
    }

    private void doAnimatorClose(View view,int index,int total,int radius){
        if(view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index/((total -1) * 2);

        int translationX = - (int)(radius*Math.sin(degree));
        int translationY = - (int)(radius*Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",translationX,0),
                ObjectAnimator.ofFloat(view,"translationY",translationY,0),
                ObjectAnimator.ofFloat(view,"scaleX",1f,0.1f),
                ObjectAnimator.ofFloat(view,"scaleY",1f,0.1f),
                ObjectAnimator.ofFloat(view,"alpha",1f,0f)
        );
        animatorSet.setDuration(500).start();

    }

    private void doAnimatorOpen(View view,int index,int total,int radius){
        if(view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.toRadians(90)/(total-1) * index;
        int translationX = - (int)(radius*Math.sin(degree));
        int translationY = - (int)(radius*Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,translationX),
                ObjectAnimator.ofFloat(view,"translationY",0,translationY),
                ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
                ObjectAnimator.ofFloat(view,"scaleY",0f,1f),
                ObjectAnimator.ofFloat(view,"alpha",0f,1f)
        );
        animatorSet.setDuration(500).start();
    }
}

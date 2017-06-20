package com.example.focus.demo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(getView());
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView()
    {
        relativeLayout=new RelativeLayout(this);
        relativeLayout.setBackground(getResources().getDrawable(R.drawable.drawable_animation_list));
        RelativeLayout.LayoutParams rparams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        rparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,R.id.textview);
        relativeLayout.setLayoutParams(rparams);

        TextView tv=new TextView(this);
        tv.setText(       "Wait for some magic to happen");
        tv.setTextSize(35f);
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setGravity(Gravity.CENTER);
        tv.setId(R.id.textview);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);


        animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);

        relativeLayout.addView(tv,rparams);
        return  relativeLayout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler=new Handler();
        Runnable r=new Runnable() {
            public void run() {
                if (animationDrawable != null && !animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            }
        };
        handler.postDelayed(r, 3000);

    }
}

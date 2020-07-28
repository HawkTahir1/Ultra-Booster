package com.signal.mobilebooster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BatteryOptimizer extends AppCompatActivity {


    ImageView loaders,staro,stars,starf,star;
    ConstraintLayout consts ;
    LinearLayout linearLayouts,zoro;
    Window window;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_optimizer);



        consts = (ConstraintLayout) findViewById(R.id.qoqo);
        loaders = (ImageView) findViewById(R.id.loader);
        zoro = (LinearLayout) findViewById( R.id.llpo);


        linearLayouts = (LinearLayout) findViewById(R.id.linearLayout);
        tv = (TextView) findViewById(R.id.textView5);

        tv.setVisibility(View.INVISIBLE);


        final Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);


        Animation something = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.kuchbhe);
        loaders.startAnimation(something);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                loaders.clearAnimation();
                loaders.setVisibility(View.INVISIBLE);
                zoro.startAnimation(blink);


                consts.setBackgroundColor(Color.parseColor("#1ece6c"));


                if(Build.VERSION.SDK_INT>=21){
                    window=BatteryOptimizer.this.getWindow();
                    window.setStatusBarColor(BatteryOptimizer.this.getResources().getColor(R.color.green));

                }

                tv.setVisibility(View.VISIBLE);



            }
        },10000);




    }
}
package com.signal.mobilebooster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class Cpucooler extends AppCompatActivity {


    public Handler handlers = new Handler();
    ImageView pngscam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpucooler);


        pngscam = (ImageView) findViewById(R.id.pngscanner);



        Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        pngscam.startAnimation(animSlideDown);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
                pngscam.startAnimation(aniSlide);


            }
        }, 3500);



        handlers.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent y = new Intent(Cpucooler.this, Cpuappplist.class);
                startActivity(y);
                finish();

            }
        }, 7000);

    }

    @Override
    public void onBackPressed() {
        // stop Handler
//        handlers.removeCallbacks(runnable);
        handlers.removeCallbacksAndMessages(null);
        // to stop anonymous runnable use handler.removeCallbacksAndMessages(null);
        finish();
    }
}
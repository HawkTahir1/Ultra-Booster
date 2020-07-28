package com.signal.mobilebooster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BatterySaver extends AppCompatActivity {
    ImageView redara,chromea,calla,messagea,setta;

    private ProgressBar progressBasr;
    private int progressStatus = 0;
    private TextView textView,tt;
    private Handler handlerEae = new Handler();
    public Handler handlerssesss = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_saver);


        redara = (ImageView) findViewById(R.id.redar);
        chromea = (ImageView) findViewById(R.id.imchrome);
        calla = (ImageView) findViewById(R.id.imcall);
        setta = (ImageView) findViewById(R.id.imsetting);
        messagea = (ImageView) findViewById(R.id.immail);
        tt = (TextView) findViewById(R.id.power);



        Animation fadeins = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        redara.startAnimation(fadeins);



        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        chromea.startAnimation(blink);
        calla.startAnimation(blink);
        setta.startAnimation(blink);
        messagea.startAnimation(blink);
        tt.startAnimation(blink);



        final Handler handlers = new Handler();
        handlers.postDelayed(new Runnable() {
            @Override
            public void run() {
                redara.animate().rotation(1080f).setDuration(8000);
            }
        }, 2500);


        final Handler handlerss = new Handler();
        handlerss.postDelayed(new Runnable() {
            @Override
            public void run() {
                chromea.clearAnimation();
                calla.clearAnimation();
                setta.clearAnimation();
                messagea.clearAnimation();
                tt.clearAnimation();

            }
        }, 10500);



        progressBasr = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handlerEae.post(new Runnable() {
                        public void run() {
                            progressBasr.setProgress(progressStatus);

                            String pp = String.valueOf(progressStatus+"%");
                            textView.setText(pp);
                        }
                    });
                    try {

                        Thread.sleep(105);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




        handlerssesss.postDelayed(new Runnable() {
            @Override
            public void run() {



                Intent ias = new Intent(BatterySaver.this,BatteryOptimizer.class);
                startActivity(ias);
                finish();







            }
        }, 10500);







    }
    @Override
    public void onBackPressed() {

        handlerssesss.removeCallbacksAndMessages(null);

        finish();
    }


}
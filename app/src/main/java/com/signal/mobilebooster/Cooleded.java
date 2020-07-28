package com.signal.mobilebooster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class Cooleded extends AppCompatActivity {


    TextView sasa,cc;
    private Handler handlerE = new Handler();
    ImageView fan,tick;
    private int progressStatus = 0;
    CircularProgressIndicator cpb;
    Window window;
    ConstraintLayout lls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooleded);


        sasa = (TextView) findViewById(R.id.kento);
        cc = (TextView)  findViewById(R.id.cdtv);

        fan = (ImageView) findViewById(R.id.fan);

        cpb = (CircularProgressIndicator) findViewById(R.id.circular_progress3);

        lls = (ConstraintLayout) findViewById(R.id.lls);
        fan.animate().rotation(5400f).setDuration(15000);



        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    handlerE.post(new Runnable() {
                        public void run() {
                            cpb.setProgress(progressStatus,100);

                            String pp = String.valueOf(progressStatus+"%");


                           cc.setVisibility(View.VISIBLE);





                        }
                    });
                    try {
                        // Sleep for 1500 milliseconds.
                        Thread.sleep(150);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        final Handler handlerss = new Handler();
        handlerss.postDelayed(new Runnable() {
            @Override
            public void run() {


                sasa.setVisibility(View.VISIBLE);

                cc.setVisibility(View.INVISIBLE);


                Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                sasa.startAnimation(blink);

                lls.setBackgroundColor(Color.parseColor("#1ece6c"));
                if(Build.VERSION.SDK_INT>=21){
                    window=Cooleded.this.getWindow();
                    window.setStatusBarColor(Cooleded.this.getResources().getColor(R.color.green));

                }


            }
        }, 15000);





    }


}
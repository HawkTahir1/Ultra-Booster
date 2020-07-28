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
import android.widget.ProgressBar;
import android.widget.TextView;

public class BoostDone extends AppCompatActivity {
    ImageView circles,rockets;
    TextView percent,opti,fsa,csps;
    private Handler handlerE = new Handler();
    ConstraintLayout constids;

    LinearLayout linearLayout;
    Window window;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boost_done);


        opti = (TextView) findViewById(R.id.opticle);
        circles = (ImageView) findViewById(R.id.circles);
        rockets = (ImageView) findViewById(R.id.rrc);
        constids = (ConstraintLayout) findViewById(R.id.constid);
        fsa = (TextView) findViewById(R.id.fsss);
        csps = (TextView) findViewById(R.id.cpsaaa);

        linearLayout = (LinearLayout) findViewById(R.id.llb);
        csps.setVisibility(View.VISIBLE);


        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        csps.startAnimation(blink);


        circles.animate().rotation(1440f).setDuration(8000);
        fsa.setVisibility(View.INVISIBLE);
        opti.setVisibility(View.INVISIBLE);

        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        rockets.startAnimation(bounce);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        percent = (TextView) findViewById(R.id.precent);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handlerE.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);

                            String pp = String.valueOf(progressStatus+"%");
                            percent.setText(pp);






                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(80);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();





        final Handler handlersse = new Handler();
        handlersse.postDelayed(new Runnable() {
            @Override
            public void run() {

                csps.clearAnimation();
                csps.setVisibility(View.INVISIBLE);

                Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                opti.startAnimation(blink);
                linearLayout.startAnimation(blink);

                opti.setVisibility(View.VISIBLE);
                constids.setBackgroundColor(Color.parseColor("#1ece6c"));




                if(Build.VERSION.SDK_INT>=21){
                    window=BoostDone.this.getWindow();
                    window.setStatusBarColor(BoostDone.this.getResources().getColor(R.color.green));

                }


                Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sd);
                fsa.startAnimation(animSlideDown);

                fsa.setVisibility(View.VISIBLE);



            }
        }, 8000);






    }
}
package com.signal.mobilebooster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.RandomAccessFile;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class MainActivity extends AppCompatActivity {



    TextView batterypercentage,ram;
    ImageView coolerinmg,boost,battery,abv;
    Button bttsl;


    CircularProgressIndicator circularProgress,circularProgress2 ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP | Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bttsl = (Button) findViewById(R.id.obtn);
        batterypercentage = (TextView) findViewById(R.id.battery);
       ram = (TextView) findViewById(R.id.RAM);


        circularProgress = findViewById(R.id.circular_progress);


        abv = (ImageView) findViewById(R.id.abv);
        coolerinmg = (ImageView) findViewById(R.id.coolerimage);
        boost = (ImageView) findViewById(R.id.boost);
        battery = (ImageView) findViewById(R.id.Batterysss);


        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        Intent sp = new Intent(MainActivity.this,BatterySaver.class);
                        startActivity(sp);


                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(MainActivity.this, "Check App Settings To Granted", Toast.LENGTH_SHORT).show();

                    }
                };

                TedPermission.with(MainActivity.this)
                        .setPermissionListener(permissionListener)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .check();



            }
        });


        boost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(MainActivity.this,Boost.class);
                startActivity(p);


            }
        });


        coolerinmg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Cpucooler.class);
                startActivity(i);

            }
        });

        abv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ise = new Intent(MainActivity.this,About.class);
                startActivity(ise);

            }
        });


        bttsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intea = new Intent(MainActivity.this,BoostDone.class);
                startActivity(intea);
                finish();
            }
        });


        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        batterypercentage.setText(batLevel+"%");
        circularProgress.setProgress(batLevel,100);


        ram.setText(getUsedMemorySize()+"MB"+"/"+getTotalRAM());














    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTotalRAM() {

        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam = 0;
        String lastValue = "";
        try {
            try {
                reader = new RandomAccessFile("/proc/meminfo", "r");
                load = reader.readLine();
            } catch (Exception e) {

            }

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
                // System.out.println("Ram : " + value);
            }
            try {
                reader.close();
            } catch (Exception e) {

            }

            totRam = Double.parseDouble(value);
            // totRam = totRam / 1024;

            double mb = totRam / 1024.0;
            double gb = totRam / 1048576.0;
            double tb = totRam / 1073741824.0;

            if (tb > 1) {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            } else if (gb > 1) {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            } else if (mb > 1) {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            } else {
                lastValue = twoDecimalForm.format(totRam).concat(" KB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Streams.close(reader);
        }


        return lastValue;
    }

    public long getUsedMemorySize() {

        try {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            long availableMegs = mi.availMem / 1048576L;

            return availableMegs;
        }
        catch(Exception e)
        {
            return 200 ;
        }

    }

    public void checkpermission(View view){


    }
}
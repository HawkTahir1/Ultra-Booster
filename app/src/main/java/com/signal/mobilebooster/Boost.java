package com.signal.mobilebooster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boost extends AppCompatActivity {
    TextView pere;
    TextView tev,teemp;
    Button cdbtn;
    private List<Boost.AppList> installedApps;
    private Boost.AppAdapter installedAppAdapters;
    LinearLayout linearLayout;
    Window window;
    ListView userInstalledApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boost);



        userInstalledApps = (ListView) findViewById(R.id.installed_app_liste);
        tev = (TextView) findViewById(R.id.ttv2);
        teemp = (TextView) findViewById(R.id.ttv3);
        cdbtn = (Button) findViewById(R.id.vvt);
        linearLayout = (LinearLayout) findViewById(R.id.LLL);
        pere = (TextView) findViewById(R.id.fdfd);


        if(Build.VERSION.SDK_INT>=21){
            window=Boost.this.getWindow();
            window.setStatusBarColor(Boost.this.getResources().getColor(R.color.orange));
        }

        Random r = new Random();
        int a = r.nextInt(5);

        List<String> listOfString = new ArrayList<String>();
        listOfString.add("68");
        listOfString.add("69");
        listOfString.add("70");
        listOfString.add("72");
        listOfString.add("74");
        String value = listOfString.get(a);

        pere.setText(value);


        installedApps = getInstalledApps();
        installedAppAdapters = new Boost.AppAdapter(Boost.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapters);



        String  tapp = userInstalledApps.getCount()+"";
        TextView countApps = (TextView)findViewById(R.id.countApps);
        countApps.setText("Installed Apps: "+tapp);



        cdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent r = new Intent(Boost.this,BoostDone.class);
                startActivity(r);
                finish();
            }
        });





    }

    private List<Boost.AppList> getInstalledApps() {
        PackageManager pm = getPackageManager();
        List<Boost.AppList> apps = new ArrayList<Boost.AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!isSystemPackage(p))) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String packages = p.applicationInfo.packageName;
                apps.add(new AppList(appName, icon, packages));
            }
        }
        return apps;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }


    public static class AppAdapter extends BaseAdapter {

        public LayoutInflater layoutInflater;
        public List<Boost.AppList> listStorage;

        public AppAdapter(Context context, List<Boost.AppList> customizedListView) {
            layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listStorage = customizedListView;
        }

        @Override
        public int getCount() {
            return listStorage.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Boost.AppAdapter.ViewHolder listViewHolder;
            if(convertView == null){
                listViewHolder = new Boost.AppAdapter.ViewHolder();
                convertView = layoutInflater.inflate(R.layout.installed_app_list, parent, false);

                listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.list_app_name);
                listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.app_icon);


                convertView.setTag(listViewHolder);
            }else{
                listViewHolder = (Boost.AppAdapter.ViewHolder)convertView.getTag();
            }
            listViewHolder.textInListView.setText(listStorage.get(position).getName());
            listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());


            return convertView;
        }

        static class ViewHolder{
            TextView textInListView;
            ImageView imageInListView;

        }
    }


    public class AppList {
        private String name;
        Drawable icon;
        private String packages;
        public AppList(String name, Drawable icon, String packages) {
            this.name = name;
            this.icon = icon;
            this.packages = packages;
        }
        public String getName() {
            return name;
        }
        public Drawable getIcon() {
            return icon;
        }
        public String getPackages() {
            return packages;
        }

    }



}
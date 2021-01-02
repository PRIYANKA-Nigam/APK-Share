package com.example.apkshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<App> apps=new ArrayList<>();
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.rec);
        PackageManager pm=getApplicationContext().getPackageManager();
        List<ApplicationInfo> packages =pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packInfo : packages){
            String name;
            if ((name=String.valueOf(pm.getApplicationLabel(packInfo))).isEmpty()){
                name= packInfo.packageName;
            }
            Drawable icon=pm.getApplicationIcon(packInfo);
            String apkPath=packInfo.sourceDir;
            long apkSize=new File(packInfo.sourceDir).length();
            apps.add(new App(name,icon,apkPath,apkSize));

        }
        Collections.sort(apps, new Comparator<App>() {
            @Override
            public int compare(App o1, App o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        AppAdapter appAdapter=new AppAdapter(this,apps);
        recyclerView.setAdapter(appAdapter);
    }
}
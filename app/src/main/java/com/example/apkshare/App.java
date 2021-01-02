package com.example.apkshare;

import android.graphics.drawable.Drawable;

public class App {
    private String name;
    private Drawable icon;
    private String appPath;
    private long appSize;

    public App(String name, Drawable icon, String appPath, long appSize) {
        this.name = name;
        this.icon = icon;
        this.appPath = appPath;
        this.appSize = appSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public long getAppSize() {
        return appSize;
    }

    public void setAppSize(long appSize) {
        this.appSize = appSize;
    }
}

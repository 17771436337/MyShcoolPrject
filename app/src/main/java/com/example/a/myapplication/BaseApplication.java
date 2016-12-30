package com.example.a.myapplication;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class BaseApplication extends Application {

    //public static LocationService locationService;
    public Vibrator mVibrator;
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mMainHandler;
    private static BaseApplication baseApplication;
    public static Activity mCurrentActivity;

    public static BaseApplication getInstance() {
        if (null == baseApplication) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }

    // 应用程序的入口
    @Override
    public void onCreate() {

        /**
         * 初始化个推
         */
        //PushManager.getInstance().initialize(this);

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        //locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        //SDKInitializer.initialize(getApplicationContext());


        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .writeDebugLogs()
                .defaultDisplayImageOptions(defaultOptions)
                .diskCacheExtraOptions(480, 320, null)
                .build();
        ImageLoader.getInstance().init(config);
        super.onCreate();

        // 上下文
        mContext = getApplicationContext();

        // 主线程
        mMainThread = Thread.currentThread();

        // mMainThreadId = mMainThread.getId();
        mMainThreadId = android.os.Process.myTid();

        mMainLooper = getMainLooper();

        // 创建主线程的handler
        mMainHandler = new Handler();

    }
}

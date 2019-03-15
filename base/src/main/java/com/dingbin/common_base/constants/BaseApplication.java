package com.dingbin.common_base.constants;

import android.app.Application;
import android.content.Context;

import com.dingbin.common_base.BuildConfig;

/**
 * Created by dingbin on 2018/7/18.
 */

public class BaseApplication extends Application {

    public static final boolean IS_DEBUG = BuildConfig.DEBUG;
    public static BaseApplication mBaseApplication;
    private ActivityControl mActivityControl;


    @Override
    public void onCreate() {
        super.onCreate();
        mActivityControl = new ActivityControl();
//        RouterConfig.init(this, BuildConfig.DEBUG);
//        AutoLayoutConifg.getInstance().useDeviceSize();
//        //调试工具
//        Stetho.initializeWithDefaults(this);
//
//        // 初始化Logger工具
//        Logger.addLogAdapter(new AndroidLogAdapter(){
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
//            }
//        });

    }
    public static BaseApplication getAppContext(){
        return mBaseApplication;
    }
    public ActivityControl getActivityControl() {
        return mActivityControl;
    }
    public void initBugly(){


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mBaseApplication = this;
        //MultiDex分包方法 必须最先初始化
        //MultiDex.install(this);
    }
    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }
    /**
     * 退出应用
     */
    public void exitApp() {
        mActivityControl.finishiAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}

package com.dingbin.commonmodel;

import android.app.Application;


/**
 * Created by dingbin on 2018/11/14.
 */

public class App extends Application {

    public static App myApplication;

    public static App getMyApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication =this;
//        AutoSizeConfig.getInstance()
//                .setCustomFragment(true)
//                .setOnAdaptListener(new onAdaptListener() {
//                    @Override
//                    public void onAdaptBefore(Object target, Activity activity) {
//                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
//                    }
//
//                    @Override
//                    public void onAdaptAfter(Object target, Activity activity) {
//                        LogUtils.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
//                    }
//                });
    }


//    /**
//     * 程序终止的时候执行
//     */
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//        exitApp();
//    }
    /**
     * 退出应用
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}

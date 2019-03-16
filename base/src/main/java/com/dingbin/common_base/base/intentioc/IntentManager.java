package com.dingbin.common_base.base.intentioc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 受 https://www.jianshu.com/p/f59e17eb996f 启发，想想确实存在一个比较麻烦的东西
 * 每次需要传递数据给其他页面的时候，都要在其他页面写接收的逻辑，这个框架可以很好的解决这个问题
 * 使用的时候启动页只负责输入就好了，接收端通过注解就可以接受到参数，省了不少麻烦
 * @author dingbin
 * @date 2019/3/16 15:20
 */

public class IntentManager {

    private static final String TAG = "IntentManager";
    public static  IntentManager instance;
    private IntentManager(){

    }

    public static IntentManager getInstance(){
        if (instance==null){
            synchronized (IntentManager.class){
                if (instance==null){
                    instance = new IntentManager();
                }
            }
        }
        return instance;
    }

    /**
     * 注入
     * @param activity
     * @param inject
     */
    public void inject(Activity activity,boolean inject) {
        if (!inject) {
            return;
        }
        Class<?> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Bundle mBundle;
        try {
            Method method = aClass.getMethod("getIntent");
            Intent intent = (Intent) method.invoke(activity);
            mBundle = intent.getExtras();
            for (Field field : fields) {
                IntentInject annotation = field.getAnnotation(IntentInject.class);
                if (annotation!=null){
                    String fieldName = field.getName();
                    Object o = mBundle.get(fieldName);
                    if (o==null){
                        Log.e(TAG, fieldName+"值未传入！");
                        //throw new RuntimeException(fieldName+"值未传入！");
                        continue;
                    }
                    field.setAccessible(true);
                    field.set(activity,o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

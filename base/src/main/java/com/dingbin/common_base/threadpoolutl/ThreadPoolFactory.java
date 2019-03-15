package com.dingbin.common_base.threadpoolutl;

/**
 * Created by dingbin on 2018/8/6.
 */

public class ThreadPoolFactory {
    static ThreadPoolProxy normalThreadPoolProxy;
    static ThreadPoolProxy downloadThreadPoolProxy;



    public static ThreadPoolProxy getNormalThreadPoolProxy(){
        if (normalThreadPoolProxy==null){
            synchronized (ThreadPoolFactory.class){
                if (normalThreadPoolProxy==null){
                    normalThreadPoolProxy = new ThreadPoolProxy(5,5);
                }
            }
        }
        return normalThreadPoolProxy;
    }

    public static ThreadPoolProxy getDownloadThreadPoolProxy(){
        if (downloadThreadPoolProxy==null){
            synchronized (ThreadPoolFactory.class){
                if (downloadThreadPoolProxy==null){
                    downloadThreadPoolProxy = new ThreadPoolProxy(3,3);
                }
            }
        }
        return downloadThreadPoolProxy;

    }





}

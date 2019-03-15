package com.dingbin.common_base.threadpoolutl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dingbin on 2018/8/6.
 */

public class ThreadPoolProxy {

    ThreadPoolExecutor executor;

    private int mCorePoolSize;
    private int mMaximumPoolSize;


    public ThreadPoolProxy(int mCorePoolSize, int mMaximumPoolSize) {
        this.mCorePoolSize = mCorePoolSize;
        this.mMaximumPoolSize = mMaximumPoolSize;
    }

    private void initThreadPoolExecutor(){
        if (executor!=null||executor.isShutdown()||executor.isTerminated()){
            synchronized (ThreadPoolProxy.class){
                if (executor!=null||executor.isShutdown()||executor.isTerminated()){
                    BlockingQueue workQueue = new LinkedBlockingDeque<>();
                    executor = new ThreadPoolExecutor(
                            mCorePoolSize,
                            mMaximumPoolSize,
                            3,
                            TimeUnit.SECONDS,
                            workQueue,
                            Executors.defaultThreadFactory(),
                            new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }

    }

    //执行任务
    public void execute(Runnable runnable){
        initThreadPoolExecutor();
        executor.execute(runnable);
    }

    //提交任务
    public void submit(Runnable runnable){
        initThreadPoolExecutor();
        executor.submit(runnable);
    }


    public void remove(Runnable runnable){
        initThreadPoolExecutor();
        executor.remove(runnable);
    }








}

package com.dingbin.common_base.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * des:网络管理工具
 * Created by xsf
 * on 2016.04.10:34
 */
public class NetUtils {

    private NetUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 检查网络是否可用
     *
     * @param paramContext
     * @return
     */
    public static boolean isNetConnected(Context paramContext) {
        boolean i = false;
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }
    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测3G是否连接
     */
    public static boolean is3gConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断网址是否有效
     */
    public static boolean isLinkAvailable(String link) {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity)
    {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
    public static boolean pingWai(){
        try {
            if (executeCommand("www.baidu.com",500L)==0){
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public static int executeCommand(final String command, final long timeout) throws IOException, InterruptedException, TimeoutException {
        Process process = Runtime.getRuntime().exec("ping -c 1 -w 10 "+command);
        // showMyToast(String.valueOf(process.exitValue()));
        PingWorker worker = new PingWorker(process);
        worker.start();
        try {
            worker.join(timeout);
            if (worker.exit != null){
                return worker.exit;
            } else{
                throw new TimeoutException();
            }
        } catch (InterruptedException ex) {
            worker.interrupt();
            Thread.currentThread().interrupt();
            throw ex;
        } finally {
            process.destroy();
        }
    }
   static  class PingWorker extends Thread{
        private final Process process;
        private Integer exit;

        public PingWorker(Process process){
            this.process = process;
        }
        @Override
        public void run() {
            try {
                exit = process.waitFor();
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

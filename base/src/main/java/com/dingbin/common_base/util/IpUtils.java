package com.dingbin.common_base.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by dingbin on 2018/7/31.
 */

public class IpUtils {
    public static String getLocalIpAddress() {
        try {
            String allIP = "";
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    allIP += inetAddress.getHostAddress()+"\n";
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {

                    }
                }
            }
            return allIP;
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return "获取失败";
    }

    public static String getIp(Application context){
        WifiManager wm=(WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //检查Wifi状态
        if (wm != null && !wm.isWifiEnabled()) {
            return "wifi未开启";
        }
        @SuppressLint("MissingPermission")
        WifiInfo wi=wm.getConnectionInfo();
        //获取32位整型IP地址
        int ipAdd=wi.getIpAddress();
        //把整型地址转换成“*.*.*.*”地址
        String ip=intToIp(ipAdd);
        return ip;
    }
    private static String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }

}

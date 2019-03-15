package com.dingbin.common_base.util.widgetUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

public class NetChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "NetReceiver";
    private boolean isDuankaiGuo = false;


    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "3G网络数据";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //这里要先判断wifi是否打开，如果关闭的话就提示wifi关闭，如果开启就判断网络是否有效，
        // 如果无效就每隔5s判断一次是否有效，如果有效就停止请求，并执行业务逻辑
        //wifi连接上与否
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())){
            //拿到wifi的状态值
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_NEW_STATE,0);
            Log.i(TAG,"wifiState = "+ wifiState);
            switch (wifiState){
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    break;
            }
        }
        //监听wifi的连接状态即是否连接的一个有效的无线路由
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())){
            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (parcelableExtra != null){
                // 获取联网状态的NetWorkInfo对象
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                //获取的State对象则代表着连接成功与否等状态
                NetworkInfo.State state = networkInfo.getState();
                //判断网络是否已经连接
                boolean isConnected = state == NetworkInfo.State.CONNECTED;
                Log.i(TAG, "isConnected:" + isConnected);
                if (isConnected) {
                    Log.e(TAG, "onReceive: 连接到一个有效路由了");

                } else {
                    Log.e(TAG, "onReceive: 没有连接到一个有效路由");
                }
            }
        }

        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI
                            || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Log.i(TAG, getConnectionType(info.getType()) + "连上");
                        if (isDuankaiGuo) {
                            //
                            isDuankaiGuo = false;
                        }
                    }
                } else {
                    Log.i(TAG, getConnectionType(info.getType()) + "断开");
                    isDuankaiGuo = true;
                }
            }
        }

    }
}

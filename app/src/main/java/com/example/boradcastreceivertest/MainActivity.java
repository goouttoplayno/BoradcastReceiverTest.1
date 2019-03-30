package com.example.boradcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private netWorkChangeReceiver netWorkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态注册：创建一个intentfilter的实例，添加网络变化的广播
        //对组件进行过滤，只获取需要的信息。
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //创建networkchangereceiver的实例，调用registerreciver方法进行注册
        netWorkChangeReceiver = new netWorkChangeReceiver();
        registerReceiver(netWorkChangeReceiver, intentFilter);


    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeReceiver);
    }

    class netWorkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "network is avaliavle", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, "network is unavaliavle", Toast.LENGTH_LONG).show();
            }
        }
    }


}

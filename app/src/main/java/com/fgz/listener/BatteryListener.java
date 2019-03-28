package com.fgz.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * 作者： FGZ
 * 功能： 电池监听
 * 创建日期： 2019-03-22
 */
public class BatteryListener {
    private Context mContext;
    private BatteryBroadcastReceiver receiver;//声明内部广播
    private BatteryStateListener mBatteryStateListener;

    public BatteryListener(Context context){
        mContext = context;
        receiver = new BatteryBroadcastReceiver();
    }

    /**
     * 注册广播
     */
    public void register(BatteryStateListener listener){
        mBatteryStateListener = listener;
        if(receiver != null){
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_BATTERY_CHANGED);//电量改变
            filter.addAction(Intent.ACTION_BATTERY_LOW); //电量过低的
            filter.addAction(Intent.ACTION_BATTERY_OKAY); //电量充满的
            filter.addAction(Intent.ACTION_POWER_CONNECTED); //链接充电线充电的
            filter.addAction(Intent.ACTION_POWER_DISCONNECTED);  //断开充电线的
            mContext.registerReceiver(receiver, filter);
        }
    }

    /**
     * 解除广播的注册
     */
    public void unregister(){
        if(receiver!=null){
            mContext.unregisterReceiver(receiver);
        }
    }
    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        int intLevel = 0;
        int intScale = 0;
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String acyion = intent.getAction();
                switch (acyion) {
                    case Intent.ACTION_BATTERY_CHANGED://电量发生改变
                        intLevel = intent.getIntExtra("level",0);
                        intScale = intent.getIntExtra("scale",100);
                        int levelPercent = (int)(((float)intLevel/intScale)*100);
                        if (mBatteryStateListener != null) {
                            Log.e("zhang", "BatteryBroadcastReceiver --> onReceive--> ACTION_BATTERY_CHANGED");
                            mBatteryStateListener.onStateChanged(levelPercent);
                        }
                        break;
                    case Intent.ACTION_BATTERY_LOW://电量低
                        if (mBatteryStateListener != null) {
                            Log.e("zhang", "BatteryBroadcastReceiver --> onReceive--> ACTION_BATTERY_LOW");
                            mBatteryStateListener.onStateLow();
                        }
                        break;
                    case Intent.ACTION_BATTERY_OKAY://电量充满
                        if (mBatteryStateListener != null) {
                            Log.e("zhang", "BatteryBroadcastReceiver --> onReceive--> ACTION_BATTERY_OKAY");
                            mBatteryStateListener.onStateOkay();
                        }
                        break;
                    case Intent.ACTION_POWER_CONNECTED://接通电源
                        if (mBatteryStateListener != null) {
                            Log.e("zhang", "BatteryBroadcastReceiver --> onReceive--> ACTION_POWER_CONNECTED");
                            mBatteryStateListener.onStatePowerConnected();
                        }
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED://拔出电源
                        if (mBatteryStateListener != null) {
                            Log.e("zhang", "BatteryBroadcastReceiver --> onReceive--> ACTION_POWER_DISCONNECTED");
                            mBatteryStateListener.onStatePowerDisconnected();
                        }
                        break;
                }
            }
        }
    }

    /**
     * 自定义接口
     * */
    public interface BatteryStateListener {
        public void onStateChanged(Integer levelPercent); //状态改变

        public void onStateLow(); //电池电量低

        public void onStateOkay();  //电池电量已满

        public void onStatePowerConnected();  //链接充电线

        public void onStatePowerDisconnected();  //断开充电线
    }
}

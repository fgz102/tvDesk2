package com.fgz.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fgz.R;
import com.fgz.activity.base.BaseActivity;
import com.fgz.fragment.EntertainmentFragment;
import com.fgz.fragment.MyFragment;
import com.fgz.fragment.SettingFragment;
import com.fgz.fragment.SquareFragment;
import com.fgz.fragment.StudyFragment;
import com.fgz.fragment.TalentFragment;
import com.fgz.listener.BatteryListener;
import com.fgz.utils.DensityUtils;
import com.fgz.utils.NetWorkUtil;
import com.fgz.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    public RadioGroup radioGroup;//底部切换按钮
    private List<Fragment> fragments;//Fragment集合
    private TextView tvTime;//显示时间
    private ImageView imgNetWorkState;//显示WIFI
    private BatteryListener listener;//声明电池的监听
    private TextView batteryTxt;
    private TextView tvTitle;//顶部标题
    private static final boolean d = false;
    private Context context;
    /**
     * 设置界面的布局
     * @return
     */
    @Override
    public int setLayoutID() {
        return R.layout.activity_main;
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        DensityUtils.init(this);
        radioGroup = (RadioGroup) findViewById(R.id.bottombar);//初始化控件
        fragments = new ArrayList<>();//初始化集合
        Float m= DensityUtils.getDensity();
        Integer d= DensityUtils.getSysWidth("w");
        Integer h = DensityUtils.getSysWidth("h");
        Integer d1=DensityUtils.getDpi();
        Log.i("fgz","宽度："+d+"密度："+m+"高度："+h+"dpi:"+d1);
        //显示标题
        showTitle();
        //显示时间
        showTime();
        //显示WIFI
        showWifi();
        //显示电池
        showBattery();
    }

    private void showTitle() {
        tvTitle = (TextView) findViewById(R.id.top_title);
        tvTitle.setText("学习");
    }

    @Override
    protected void onDestroy() {
        if (listener != null) {
            listener.unregister();  //解除注册
        }
        super.onDestroy();
    }

    /**
     * 设置监听
     */
    @Override
    public void setListener() {
        radioGroup.setOnCheckedChangeListener(this);//设置单选的选择监听
        /**
         * 注册电池监听
         */
        listener.register(new BatteryListener.BatteryStateListener() {
            @Override
            public void onStateChanged(Integer levelPercent) {//状态改变
                Log.e("fgz","MainActivity-->onStateChanged->"+levelPercent);
                batteryTxt.setText("电量"+levelPercent+"%");
            }

            @Override
            public void onStateLow() {   //电池电量低
                Log.e("fgz","MainActivity --> onStateLow--> ");
                Toast.makeText(MainActivity.this,"电量低",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStateOkay() {  //电池电量已满
                Log.e("fgz", "MainActivity --> onStateOkay--> ");
                Toast.makeText(MainActivity.this, "电量已满", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatePowerConnected() {  //链接充电线
                Log.e("fgz", "MainActivity --> onStatePowerConnected--> ");
                Toast.makeText(MainActivity.this, "链接充电线", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatePowerDisconnected() {  //断开充电线
                Log.e("fgz", "MainActivity --> onStatePowerDisconnected--> ");
                Toast.makeText(MainActivity.this, "断开充电线", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 设置功能
     */
    @Override
    public void setFunction() {
        radioGroup.check(R.id.yg_study);//默认展示学习界面

    }


    /**
     * 系统时间显示
     */
    private Handler timeHandle= new Handler();
    public void showTime(){
        //获取组件id
        tvTime = (TextView) findViewById(R.id.top_time);
        //立即发送Runnable对象
        timeHandle.post(timeRun);
    }

    private Runnable timeRun =new Runnable() {
        @Override
        public void run() {
            setTvTimeText(TimeUtils.getTime());
            timeHandle.postDelayed(this,1000);
        }
    };
    public void setTvTimeText(String text) {
       tvTime.setText(text);
    }
    /**
     * 显示电池
     */
    public void showBattery(){
        batteryTxt = (TextView) findViewById(R.id.top_battery);
        listener = new BatteryListener(this); //初始化电池的监听
    }
    /**
     * Wifi 信号强度
     */
    public void showWifi(){
        imgNetWorkState = (ImageView) findViewById(R.id.top_wifi);
        this.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        this.registerReceiver(wifiChange, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
    }
    private BroadcastReceiver wifiChange = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo.getBSSID()!= null){
                //wifi信号强度
                int signalLevel = WifiManager.calculateSignalLevel(wifiInfo.getRssi(),4);
                if(signalLevel == 0){
                    imgNetWorkState.setImageResource(R.drawable.wifi_1);
                }else if (signalLevel == 1) {
                    imgNetWorkState.setImageResource(R.drawable.wifi_2);
                } else if (signalLevel == 2) {
                    imgNetWorkState.setImageResource(R.drawable.wifi_3);
                } else if (signalLevel == 3) {
                    imgNetWorkState.setImageResource(R.drawable.networkstate_on);
                }
                if (d) {
                    Toast.makeText(context, "wifi level" + signalLevel, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 网络状态
     */
    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkInfo currentNetworkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            int type = currentNetworkInfo.getSubtype();

            if(currentNetworkInfo.isConnected()&& type==ConnectivityManager.TYPE_WIFI){
                if(d){
                    Toast.makeText(context, "Connected",Toast.LENGTH_LONG).show();
                }
                imgNetWorkState.setImageResource(R.drawable.networkstate_on);
            }else if(currentNetworkInfo.isConnected() && type == ConnectivityManager.TYPE_ETHERNET){
                //以太网
                imgNetWorkState.setImageResource(R.drawable.networkstate_ethernet);
            }else if(!currentNetworkInfo.isConnected()){
                //无网络
                imgNetWorkState.setImageResource(R.drawable.networkstate_off);
            }
            if (d) {
                String text = "currentNetworkInfo=>>" + NetWorkUtil.isNetWorkConnected(context);
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        }
    };


    /**
     * 选择切换界面
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.yg_study://学习
                selectFragmentShow(transaction, StudyFragment.class);
                tvTitle.setText("学习");
                break;
            case R.id.yg_talent://才艺
                selectFragmentShow(transaction, TalentFragment.class);
                tvTitle.setText("才艺");
                break;
            case R.id.yg_entertainment://丫哥乐园
                selectFragmentShow(transaction, EntertainmentFragment.class);
                tvTitle.setText("丫哥乐园");
                break;
            case R.id.yg_square://广场
                selectFragmentShow(transaction, SquareFragment.class);
                tvTitle.setText("广场");
                break;
            case R.id.yg_setting://设置
                selectFragmentShow(transaction, SettingFragment.class);
                tvTitle.setText("设置");
                break;
            case R.id.yg_my://我的
                selectFragmentShow(transaction, MyFragment.class);
                tvTitle.setText("我的");
                break;
        }
        transaction.commit();
    }
    /**
     * 选择要显示的Fragment
     *
     * @param transaction
     * @param <T>
     * @return
     */
    private   <T extends Fragment> T selectFragmentShow(FragmentTransaction transaction, Class<T> clazz) {
        T fragment = null;
        boolean isHave = false;
        if (fragments.size() == 0) {//默认就添加
            fragment = addFragment(transaction, clazz);
            return fragment;
        }
        //判断集合中是否有这个元素
        for (int i = 0, length = fragments.size(); i < length; i++) {
            fragment = (T) fragments.get(i);
            if (fragment.getClass().equals(clazz)) {
                transaction.show(fragment);
                isHave = true;
                continue;
            }
            transaction.hide(fragments.get(i));
        }
        if (isHave == false) {
            fragment = addFragment(transaction, clazz);
        }
        return fragment;
    }
    /**
     * 添加元素
     *
     * @param transaction
     * @param clazz
     * @param <T>
     * @return
     */
    public  <T extends Fragment> T addFragment(FragmentTransaction transaction, Class<T> clazz) {
        T fragment = null;
        try {
            fragment = clazz.newInstance();
            transaction.add(R.id.frag_home, fragment).show(fragment);
            fragments.add(fragment);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    //

}

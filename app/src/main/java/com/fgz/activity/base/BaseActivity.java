package com.fgz.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.fgz.listener.ActivityUIListener;

/**
 * 作者： FGZ
 * 功能： Activity基类
 * 创建日期： 2019-03-05
 */
public abstract class BaseActivity extends MPermissionsActivity implements ActivityUIListener {

    /**
     * 初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateCustom();
    }

    /**
     * 初始化
     */
    protected void onCreateCustom(){
        setContentView(setLayoutID());
        initView();//初始化控件
        setListener();//设置监听
        setFunction();//设置功能
    }

    /**
     * 跳转界面
     * @param claszz
     * @param <T>
     * @return
     */
    protected final <T extends Activity>Intent activity(Class<T> claszz){
        return new Intent(this,claszz);
    }

    @LayoutRes //通过注解注入布局文件
    protected abstract int setLayoutID();
    @Override
    public void initView() {

    }
    //设置监听的方法
    @Override
    public void setListener() {

    }
    //设置功能的方法
    @Override
    public void setFunction() {

    }
}

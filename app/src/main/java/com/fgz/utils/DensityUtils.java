package com.fgz.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 作者： FGZ
 * 功能： 屏幕像素密度转换
 * 创建日期： 2019-03-07
 */
public class DensityUtils {
    private static DisplayMetrics dm;
    //初始化，只需调用一次
    public static void init(Context context)
    {
        if(dm==null) {
            dm = context.getResources().getDisplayMetrics();
        }
    }
    /**
     * 获取屏幕像素密度
     * @return
     */
    public static float getDensity() {
        return dm.density;
    }

    public  static int getDpi(){
        return dm.densityDpi;
    }
    /**
     * 获取屏幕分辨率
     * @return
     */
    public static int getSysWidth(String type) {
        switch (type){
            case "w":return dm.widthPixels;
            case "h":return dm.heightPixels;
            default:return dm.widthPixels;
        }
    }
}

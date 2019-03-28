package com.fgz.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 作者： FGZ
 * 功能： 屏幕像素密度转换
 * 创建日期： 2019-03-07
 */
public class DensityUtils {
    /**
     * 获取屏幕像素密度
     * @param context
     * @return
     */
    public static float getDpi(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }

    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int getSyWidth(Context context,String type) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        switch (type){
            case "w":return dm.widthPixels;
            case "h":return dm.heightPixels;
            default:return dm.widthPixels;
        }
    }
}

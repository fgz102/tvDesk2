package com.fgz.utils;

/**
 * 作者： FGZ
 * 功能： 计算dp，设计稿1920*1080，机器 1280*720 密度1；
 * 创建日期： 2019-03-28
 */

public class CalDp {
    public static float swWidth=1920;//设计稿屏幕宽度

    /**
     * @param sWidthOrHeight 设计稿图片宽或高
     * @param rwWidth   实体机屏幕宽
     * @param density   实体机密度
     * @return
     */
    //返回实体机图片宽高,传px
    public static int toDp(float sWidthOrHeight,float rwWidth,float density){
        int rWidthOrHeight =(int)(sWidthOrHeight*rwWidth/swWidth/density);
        return rWidthOrHeight;
    }

    public static Integer toDp(int sWidthOrHeight) {
        return toDp(sWidthOrHeight,720,1.0f);
    }
}

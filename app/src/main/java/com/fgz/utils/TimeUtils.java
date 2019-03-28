package com.fgz.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者： FGZ
 * 功能： 获取时间日期、格式化时间
 * 创建日期： 2019-03-18
 */
public class TimeUtils {

    /**
     * 获取时间
     * @return
     */
    public static String getTime(){
       String date = getFormattedDate();
       String sTime = date.substring(11,date.length());
       return sTime;
    }

    public static String getDate(){
        String date = getFormattedDate();
        String sDate = date.substring(0,11);
        return sDate;
    }

    /**
     * 获取系统时间日期
     * @return
     */
    private static String getFormattedDate(){
        Date dNow= new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate=ft.format(dNow);
        return formattedDate;
    }
}

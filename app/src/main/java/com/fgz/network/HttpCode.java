package com.fgz.network;

/**
 * 作者： FGZ
 * 功能： 请求码，用于标记什么请求
 * 创建日期： 2019-03-25
 */
public class HttpCode {
    public static final String FAILURE = "失败";
    public static final String SUCCESS = "成功";
    public static final int ERROR = 0;  //错误
    public static final int HOME = 1;   //请求首页的数据
    public static final int START_PAGE = 1; //起始页数
    public static final int RANDOM_PAGE = 3;  //随机页数
    public static final int PAGE_ROW = 10; //数据条数
}

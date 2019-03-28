package com.fgz.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者： FGZ
 * 功能： 网络请求对象类
 * 创建日期： 2019-03-25
 */
public class HttpClient {
    private OkHttpClient client; //网络请求对象

    /**
     * 私有的构造器在HttpClient实例化后，创建OkHttpClient对象，用于请求网络
     */
    private HttpClient() {
        client = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    public static class SingleTon{
        public static final HttpClient INSTANCE = new HttpClient();
    }
    /**
     * 获取单例
     * @return
     */
    public static HttpClient getInstance(){
        return SingleTon.INSTANCE;
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    public OkHttpClient getClient() {
        return client;
    }
}

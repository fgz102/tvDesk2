package com.fgz.network;

import android.os.Handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者： FGZ
 * 功能： 网络工具类，主要用于发送GET或者POST请求获取网络数据
 * 创建日期： 2019-03-25
 */
public class HttpUtils {

    /**
     * HttpGet()
     * @param handler
     * @param url
     * @param requestCode
     */
    public static void HttpGet(final Handler handler, String url, final int requestCode){
        final Request request =new Request.Builder().url(url).build(); //获取一个请求对象
        HttpClient.getInstance().getClient().newCall(request).enqueue(new Callback() {
            //请求失败时的回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                //将错误结果发送给Handler对象,然后由handleMessage（）方法处理
                handler.obtainMessage(HttpCode.ERROR,e.getMessage()).sendToTarget();
            }

            //请求成功时的回调方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //将获取的结果发送给Handler对象，然后由handleMessage（）方法处理
                handler.obtainMessage(requestCode,response.body().string()).sendToTarget();
            }
        });
    }

    public static void HttpPost(final Handler handler, String url, Map params,final int requestCode){
        FormBody.Builder builder = new FormBody.Builder(); //创建保存请求参数键值对的对象
        Iterator<Map.Entry> iterator = params.entrySet().iterator(); //获取迭代器对象
        //将请求参数以键值对的形式添加到builder对象中
        while (iterator.hasNext()){
            Map.Entry entry = iterator.next();
            String key = entry.getKey().toString();  //获取键名
            String values = entry.getValue().toString(); //获取值
            builder.add(key,values); //添加到builder对象
        }
        //获取一个请求对象
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        HttpClient.getInstance().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.obtainMessage(HttpCode.ERROR,e.getMessage()).sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.obtainMessage(requestCode,response.body().string()).sendToTarget();
            }
        });
    }
}

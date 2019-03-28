package com.fgz.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 作者： FGZ
 * 功能： 解决编码工具类
 * 创建日期： 2019-03-25
 */
public class CodeUtil {
    /**
     * 对传递参数进行Base64编码,解决乱码
     */
    public String base64(String content){
        try{
            //对字符串进行64编码
            content = Base64.encodeToString(content.getBytes("utf-8"),Base64.DEFAULT);
            content = URLEncoder.encode(content,"utf-8");//对字符串进行URL编码
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return content;
    }
}

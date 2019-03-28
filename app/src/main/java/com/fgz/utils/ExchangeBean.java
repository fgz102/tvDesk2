package com.fgz.utils;

import com.google.gson.JsonArray;

import java.util.ArrayList;

/**
 * 作者： FGZ
 * 功能： com.fgz.utils
 * 创建日期： 2019-03-26
 */
public class ExchangeBean {
    public String msg;
    public String code;
    public ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public class Data
    {
        public String id;
        public String picture;
        public String title;
    }
}


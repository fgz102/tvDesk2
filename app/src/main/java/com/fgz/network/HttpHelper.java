package com.fgz.network;

/**
 * 作者： FGZ
 * 功能： 网络访问帮助类 域名访问
 * 创建日期： 2019-03-19
 */
public class HttpHelper {
    //域名
    public static final String HTTP_URL = "http://192.168.2.253:8080/Shop/";
    //首页数据
    public static final String HOME_URL = HTTP_URL + "Home";
    //商品详情
    public static final String GOODS_DETAILS = HTTP_URL + "GoodsDetails";
    //首页推荐分类
    public static final String GOODS_TYPE = HTTP_URL + "TypeShop";
    //商品分类列表
    public static final String TYPE_LIST = HTTP_URL + "TypeList";
    //商品分类数据
    public static final String SHOP_LIST = HTTP_URL + "ShopList";
    //用户登录
    public static final String MINE_LOGIN = HTTP_URL + "Login";
}

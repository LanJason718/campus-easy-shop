package com.jason.common.constants;

public class WeChatConstants {
    //-------------------------------------------微信系统配置------------------------------------------------------------
    /*小程序appid*/
    public static final String WECHAT_MINI_APPID = "routine_appid";
    /*小程序appSecret*/
    public static final String WECHAT_MINI_APPSECRET = "routine_appsecret";

    //------------------------------------------------微信小程序------------------------------------------------
    //微信接口请求地址
    public static final String API_URL = "https://api.weixin.qq.com/";
    //获取小程序openId
    public static final String WE_CHAT_AUTHORIZE_PROGRAM_GET_OPEN_ID = "sns/jscode2session";


    /** 小程序登录凭证校验的url */
    public static final String WECHAT_MINI_SNS_AUTH_CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={}&secret={}&js_code={}&grant_type=authorization_code";


}

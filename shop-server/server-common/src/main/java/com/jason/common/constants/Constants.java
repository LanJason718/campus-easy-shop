package com.jason.common.constants;

public class Constants {
    //默认分页
    public static final int DEFAULT_PAGE = 1;

    //默认分页
    public static final int DEFAULT_LIMIT = 20;
    public static final long TOKEN_EXPRESS_MINUTES = (60 * 24);
    public static final String HEADER_AUTHORIZATION_KEY = "Authori-zation";
    public static final String USER_TOKEN_REDIS_KEY_PREFIX = "TOKEN_USER:";
    //上传类型
    public static final String UPLOAD_TYPE_IMAGE = "shopimage";
    //config表数据redis
    public static final String CONFIG_LIST = "config_list"; //配置列表
    //图片上传
    public static final String UPLOAD_IMAGE_EXT_STR_CONFIG_KEY = "image_ext_str";
    public static final String UPLOAD_IMAGE_MAX_SIZE_CONFIG_KEY = "image_max_size";


    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YEAR = "yyyy";
    public static final String DATE_FORMAT_MONTH_DATE = "MM-dd";
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";
    public static final String DATE_TIME_FORMAT_NUM = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_NUM = "yyyyMMdd";
    public static final String DATE_FORMAT_START = "yyyy-MM-dd 00:00:00";
    public static final String DATE_FORMAT_END = "yyyy-MM-dd 23:59:59";
    public static final String DATE_FORMAT_MONTH_START = "yyyy-MM-01 00:00:00";
    public static final String DATE_FORMAT_YEAR_START = "yyyy-01-01 00:00:00";
    public static final String DATE_FORMAT_YEAR_END = "yyyy-12-31 23:59:59";
    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";
    public static final String DATE_FORMAT_START_PEREND = "00:00:00";
    public static final String DATE_FORMAT_END_PEREND = "23:59:59";
    public static final String DATE_FORMAT_HHMM = "yyyy-MM-dd HH:mm";


    //会员搜索日期类型
    public static final String SEARCH_DATE_DAY = "today"; //今天
    public static final String SEARCH_DATE_YESTERDAY = "yesterday"; //昨天
    public static final String SEARCH_DATE_LATELY_7 = "lately7"; //最近7天
    public static final String SEARCH_DATE_LATELY_30 = "lately30"; //最近30天
    public static final String SEARCH_DATE_WEEK = "week"; //本周
    public static final String SEARCH_DATE_PRE_WEEK = "preWeek"; //上周
    public static final String SEARCH_DATE_MONTH = "month"; //本月
    public static final String SEARCH_DATE_PRE_MONTH = "preMonth"; //上月
    public static final String SEARCH_DATE_YEAR = "year"; //年
    public static final String SEARCH_DATE_PRE_YEAR = "preYear"; //上一年

    // 时间类型开始时间
    public static String DATE_TIME_TYPE_BEGIN = "begin";

    // 时间类型结束时间
    public static String DATE_TIME_TYPE_END = "end";
}

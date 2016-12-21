package com.example.a.myapplication.util;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/12/21.
 */
public class TimeUtils {


    /**
     * 获取时间工具
     * "YYYY-MM-dd HH:mm"
     */
    public static String getTime(long data) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sDateFormat.format(data);
        return date;
    }

}

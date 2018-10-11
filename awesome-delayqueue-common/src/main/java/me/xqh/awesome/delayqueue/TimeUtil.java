/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:TimeUtil.java
 * Date:2018/10/11
 */

package me.xqh.awesome.delayqueue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qinghua.xu
 * @date 2018/10/11
 **/
public class TimeUtil {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS sss");
    public static String getTimeStr(Date date){
        return sdf.format(date);
    }
}

/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:TestPollingAwesomeExecutor.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.scheduling;

import me.xqh.awesome.delayqueue.common.AwesomeURL;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class TestPollingAwesomeExecutor {

    @Test
    public void testExec(){
        Map<String,String> param = new HashMap<>();
        AwesomeURL url = new AwesomeURL("redis",null,null,"13.114.39.142",6379,null,param);
        PollingAwesomeExecutor pollingAwesomeExecutor = new PollingAwesomeExecutor(url);
        pollingAwesomeExecutor.execute();
    }

}

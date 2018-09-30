/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:InitRunner.java
 * Date:2018/09/20
 */

package me.xqh.awesome.delayqueue.web;

import me.xqh.awesome.delayqueue.common.AwesomeURL;
import me.xqh.awesome.delayqueue.scheduling.AwesomeExecutor;
import me.xqh.awesome.delayqueue.scheduling.PollingAwesomeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
//@PropertySource({"classpath:conf.properties"})
public class InitRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitRunner.class);

    @Autowired
    private AwesomeURL awesomeURL;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //TODO 根据配置加载
//        Map<String,String> param = new HashMap<>();
//        AwesomeURL url = new AwesomeURL("redis",null,null,"13.114.39.142",6379,null,param);

        AwesomeExecutor executor = new PollingAwesomeExecutor(awesomeURL);
        executor.execute();
    }
}

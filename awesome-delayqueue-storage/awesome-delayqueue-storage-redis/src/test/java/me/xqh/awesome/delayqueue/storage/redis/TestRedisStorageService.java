/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:TestRedisStorageService.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.storage.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.xqh.awesome.delayqueue.common.AwesomeURL;
import me.xqh.awesome.delayqueue.storage.api.AwesomeJob;
import me.xqh.awesome.delayqueue.storage.api.StorageService;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class TestRedisStorageService {

    @Test
    public void test(){
        Map<String,String> param = new HashMap<>();
        AwesomeURL url = new AwesomeURL("redis",null,null,"13.114.39.142",6379,null,param);

        StorageService storageService = new RedisStorageFactory().createStorageService(url);
//        AwesomeJob job = new AwesomeJob("111","test",20);
//        storageService.addJob(job);

        List<AwesomeJob> list =  storageService.listExpiredJobs(System.currentTimeMillis());
        for (AwesomeJob job1 :list){
            System.out.println(job1.toString());
        }
    }

    @Test
    public void testJson(){
//        String json = "{\"countDown\":1,\"delaySeconds\":15,\"expireTime\":1538216736008,\"id\":\"xu001\",\"status\":\"1\",\"topic\":\"order\",\"triggerType\":2}";
        AwesomeJob j1 = new AwesomeJob("qqq","www",14);
        j1.setStatus(1);
        j1.setData("222");

        String json = JSON.toJSONString(j1);
        System.out.println(json);
        AwesomeJob job = JSONObject.parseObject(json,AwesomeJob.class);
        System.out.println(job.toString());
    }
}

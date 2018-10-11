/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:Config.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.web;

import me.xqh.awesome.delayqueue.common.AwesomeServiceLoader;
import me.xqh.awesome.delayqueue.common.AwesomeURL;
import me.xqh.awesome.delayqueue.storage.api.StorageFactory;
import me.xqh.awesome.delayqueue.storage.api.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
@Configuration
@PropertySource({"classpath:config.properties"})
public class Config {
    @Value("${storage}")
    private String storage;

    @Bean
    public AwesomeURL awesomeUrl() throws IOException {
        String configPath = storage+".properties";
        Resource resource = new ClassPathResource(configPath);
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        Set<String> keySet = props.stringPropertyNames();
        String host = props.getProperty("host");
        int port = Integer.parseInt(props.getProperty("port"));
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        Map<String,String> paramMap = new HashMap<>();
        for (String key:keySet){
            paramMap.put(key, (String) props.get(key));
        }
        AwesomeURL awesomeURL = new AwesomeURL(storage,username,password,host,port,null,paramMap);
        return awesomeURL;
    }

    @Bean
    public StorageService storageService() throws IOException {
        AwesomeURL url = awesomeUrl();
        AwesomeServiceLoader<StorageFactory> serviceLoader = AwesomeServiceLoader.load(StorageFactory.class,storage);

        StorageFactory storageFactory = serviceLoader.getNeedClass();
        StorageService storageService = storageFactory.getStorageService(url);

//        ServiceLoader<StorageFactory> sl = ServiceLoader.load(StorageFactory.class);
//        Iterator<StorageFactory> iter = sl.iterator();
//        StorageService storageService = null;
//        while (iter.hasNext()){
//            //TODO 根据storage获取合适的StorageFactory
//            StorageFactory storageFactory = iter.next();
//            storageService = storageFactory.getStorageService(awesomeUrl());
//            break;
//        }
        return storageService;
    }
}

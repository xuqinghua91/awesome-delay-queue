/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AbstractAwesomeExecutor.java
 * Date:2018/09/28
 */

package me.xqh.awesome.delayqueue.scheduling;

import me.xqh.awesome.delayqueue.common.AwesomeServiceLoader;
import me.xqh.awesome.delayqueue.common.AwesomeURL;
import me.xqh.awesome.delayqueue.storage.api.StorageFactory;
import me.xqh.awesome.delayqueue.storage.api.StorageService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author qinghua.xu
 * @date 2018/9/28
 **/
public abstract class AbstractAwesomeExecutor implements AwesomeExecutor {
    protected StorageService storageService;
    public AbstractAwesomeExecutor(AwesomeURL url){
        init(url);
    }

    private void init(AwesomeURL url){
        AwesomeServiceLoader<StorageFactory> serviceLoader = AwesomeServiceLoader.load(StorageFactory.class,url.getStorage());

        StorageFactory storageFactory = serviceLoader.getNeedClass();
        storageService = storageFactory.getStorageService(url);
    }


}

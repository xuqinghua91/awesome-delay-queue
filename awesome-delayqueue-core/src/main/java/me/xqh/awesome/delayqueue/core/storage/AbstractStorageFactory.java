/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AbstractStorageFactory.java
 * Date:2018/09/27
 */

package me.xqh.awesome.delayqueue.core.storage;

/**
 * @author qinghua.xu
 * @date 2018/9/27
 **/
public abstract class AbstractStorageFactory implements StorageFactory {

    @Override
    public StorageService getStorageService() {
        return createStorageService();
    }

    protected abstract StorageService createStorageService() ;
}

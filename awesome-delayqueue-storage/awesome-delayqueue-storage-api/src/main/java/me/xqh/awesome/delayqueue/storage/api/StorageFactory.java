/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:StorageFactory.java
 * Date:2018/09/27
 */

package me.xqh.awesome.delayqueue.storage.api;

import me.xqh.awesome.delayqueue.common.AwesomeURL;

/**
 * @author qinghua.xu
 * @date 2018/9/27
 **/
public interface StorageFactory {
    StorageService getStorageService(AwesomeURL url);
}

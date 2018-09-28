/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AbstractStorageService.java
 * Date:2018/09/28
 */

package me.xqh.awesome.delayqueue.storage.api;

/**
 * @author qinghua.xu
 * @date 2018/9/28
 **/
public abstract class AbstractStorageService implements StorageService{
    @Override
    public boolean addJob(AwesomeJob job) {
        if (checkJobRestrict(job)){
            return false;
        }
        return true;
    }
    protected abstract boolean doAddJob(AwesomeJob job);
    protected abstract boolean checkJobRestrict(AwesomeJob job);


}

/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:RedisStorageFactory.java
 * Date:2018/09/27
 */

package me.xqh.awesome.delayqueue.storage.redis;

import me.xqh.awesome.delayqueue.storage.api.AbstractStorageFactory;
import me.xqh.awesome.delayqueue.storage.api.StorageService;
import redis.clients.jedis.JedisPool;

/**
 * @author qinghua.xu
 * @date 2018/9/27
 **/
public class RedisStorageFactory extends AbstractStorageFactory {

    private JedisPool jedisPool;
    public RedisStorageFactory(JedisPool jedisPool){
        this.jedisPool = jedisPool;
    }
    @Override
    protected StorageService createStorageService() {

        return new RedisStorageService(jedisPool);
    }
}

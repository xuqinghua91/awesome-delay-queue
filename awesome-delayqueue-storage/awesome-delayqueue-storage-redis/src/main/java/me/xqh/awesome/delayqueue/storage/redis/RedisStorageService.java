/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:RedisStorageService.java
 * Date:2018/09/28
 */

package me.xqh.awesome.delayqueue.storage.redis;

import com.alibaba.fastjson.JSON;
import me.xqh.awesome.delayqueue.storage.api.AbstractStorageService;
import me.xqh.awesome.delayqueue.storage.api.AwesomeJob;
import me.xqh.awesome.delayqueue.storage.api.AwesomeTopic;
import me.xqh.awesome.delayqueue.storage.api.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 使用redis实现job的存储及获取
 * @author qinghua.xu
 * @date 2018/9/27
 *  TODO 并发处理
 **/
public class RedisStorageService extends AbstractStorageService {
    Logger logger = LoggerFactory.getLogger(RedisStorageService.class);
    private final JedisPool jedisPool;
    public RedisStorageService(JedisPool jedisPool){
        this.jedisPool = jedisPool;
    }

    @Override
    protected boolean doAddJob(AwesomeJob job) {
        job.setStatus(RedisConstants.JobStatus.delay.getValue());
        String jobId = job.getId();
        Jedis jedis = jedisPool.getResource();
        Transaction tx = jedis.multi();
        boolean result = false;
        //TODO 改为lua script
        try {
            tx.set(RedisConstants.generateJobKey(jobId), JSON.toJSONString(job));
            switch (job.getTriggerType()){
                case RedisConstants.triggerType_expire:
                    tx.zadd(RedisConstants.generateDelayBucketKey(),job.getExpireTime(),jobId);
                    break;
                case RedisConstants.triggerType_count:
                    break;
                case RedisConstants.triggerType_all:
                    tx.zadd(RedisConstants.generateDelayBucketKey(),job.getExpireTime(),jobId);
                    break;
                default:break;
            }
            tx.exec();
            result = true;
        }catch (Exception e){
            tx.discard();
        }
        return false;
    }

    @Override
    protected boolean checkJobRestrict(AwesomeJob job) {
        //TODO 做topic、job的校验
        return true;
    }

    private void handleCountDown(Jedis jedis){
//        jedis.zadd(RedisConstants.generateCountBucketKey(),job.getExpireTime(),jobId);
    }
    @Override
    public AwesomeJob getJob(String id) {
        Jedis jedis = jedisPool.getResource();
        String json = jedis.get(RedisConstants.generateJobKey(id));
        AwesomeJob job = JSON.parseObject(json,AwesomeJob.class);
        return job;
    }

    @Override
    public void removeJob(String id) {

    }

    @Override
    public List<AwesomeJob> listExpiredJobs(long currentTime) {
        Jedis jedis = jedisPool.getResource();
        Set<String> jobSet = jedis.zrangeByScore(RedisConstants.generateDelayBucketKey(),0,currentTime);
        List<AwesomeJob> expiredList = new ArrayList<>(jobSet.size());
        for (String id: jobSet){
            String json = jedis.get(RedisConstants.generateJobKey(id));
            if (RedisConstants.isEmpty(json)){
                break;
            }
            AwesomeJob job = JSON.parseObject(json,AwesomeJob.class);
            if (job.getStatus() == RedisConstants.JobStatus.delay.getValue()){
                expiredList.add(job);
            }
        }
        return expiredList;
    }

    @Override
    public void transferExpiredJobs(List<AwesomeJob> jobList) {
        Jedis jedis = jedisPool.getResource();
        for (AwesomeJob job: jobList){
            job.setStatus(RedisConstants.JobStatus.ready.getValue());
            Transaction  tx = jedis.multi();
            try {
                tx.set(RedisConstants.generateJobKey(job.getId()),JSON.toJSONString(job));
                tx.lpush(RedisConstants.generateReadQueueKey(job.getTopic()),job.getId());
                tx.exec();
            }catch (Exception e){
                tx.discard();
                logger.error("job过期，转移到 ready queue消费出错，jobId:{},error:{}",job.getId(),e);
            }
        }
    }

    @Override
    public List<AwesomeJob> consumeReadyJobs(String topic,int number) {
        Jedis jedis = jedisPool.getResource();
        String topicListKey= RedisConstants.generateReadQueueKey(topic);
        long realLength = Math.min(number,jedis.llen(topicListKey));
        List<AwesomeJob> list = new ArrayList<>(Long.valueOf(realLength).intValue());
        for (int i = 0;i< realLength;i++){
            String id = jedis.lpop(topicListKey);
            try {
                String json = jedis.get(RedisConstants.generateJobKey(id));
                AwesomeJob job = JSON.parseObject(json,AwesomeJob.class);
                job.setStatus(RedisConstants.JobStatus.reserved.getValue());
                jedis.set(RedisConstants.generateJobKey(id), JSON.toJSONString(job));
                list.add(job);
            }catch (Exception e){
                logger.error("ready queue消费出错，jobId:{},error:{}",id,e);
            }
        }
        return list;
    }

    @Override
    public boolean addAwesomeTopic(AwesomeTopic topic) {
        //TODO
        return false;
    }

    @Override
    public AwesomeTopic getAwesomeTopic(String topic) {
        return null;
    }
}

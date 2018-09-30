/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:RedisConstants.java
 * Date:2018/09/27
 */

package me.xqh.awesome.delayqueue.storage.redis;

/**
 * @author qinghua.xu
 * @date 2018/9/27
 **/
public class RedisConstants {
    enum JobStatus{
        delay(1),ready(2),reserved(3),deleted(4);
        JobStatus(int value){
            this.value = value;
        }
        private int value;

        public int getValue() {
            return value;
        }
    }
    public static final int triggerType_expire = 0;
    public static final int triggerType_count = 1;
    public static final int triggerType_all = 2;
    /**
     * 生成redis存储job的key
     * @param jobId
     * @return
     */
    public static final String generateJobKey(String jobId){
        return "awesome-dq-job-"+jobId;
    }
    public static final String generateDelayBucketKey(){
        return "awesome-dq-delayBucket";
    }
    public static final String generateCountBucketKey(){
        return "awesome-dq-countBucket";
    }

    public static final String generateReadySetKey(String topic){
        return "awesome-dq-ready-" + topic;
    }

    public static final String generateTopicKey(String topic){
        return "awesome-dq-topic-"+topic;
    }
    public static final String generateTopicJobsSetKey(String topic){
        return "awesome-dq-topicJobsSet-"+topic;
    }
    public static final boolean isEmpty(String target){
        if (null == target || target.trim().length() == 0){
            return true;
        }
        return false;
    }

}

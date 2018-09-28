/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AwesomeJob.java
 * Date:2018/09/27
 */

package me.xqh.awesome.delayqueue.storage.api;


/**
 * @author qinghua.xu
 * @date 2018-09-26
 * job定义
 */
public class AwesomeJob {
    private String id;
    private String topic;
    private long delaySeconds;
    private final long expireTime;
    private String data;
    private int triggerType;
    private int status;
    private int countDown;

    public AwesomeJob(String id,String topic,long delaySeconds){
        this.delaySeconds = delaySeconds;
        this.id = id;
        this.topic = topic;
        this.countDown = 1;
        expireTime = System.currentTimeMillis() + delaySeconds*1000;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getDelaySeconds() {
        return delaySeconds;
    }

    public void setDelaySeconds(long delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(int triggerType) {
        this.triggerType = triggerType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCountDown() {
        return countDown;
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public String toString() {
        return "AwesomeJob{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", delaySeconds=" + delaySeconds +
                ", expireTime=" + expireTime +
                ", data='" + data + '\'' +
                ", triggerType=" + triggerType +
                ", status=" + status +
                '}';
    }
}

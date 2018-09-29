/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AwesomeTopic.java
 * Date:2018/09/28
 */

package me.xqh.awesome.delayqueue.storage.api;

import java.io.Serializable;

/**
 * @author qinghua.xu
 * @date 2018/9/28
 **/
public class AwesomeTopic implements Serializable {
    private String topic;
    private int capacity;
    private int subJobLimit;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSubJobLimit() {
        return subJobLimit;
    }

    public void setSubJobLimit(int subJobLimit) {
        this.subJobLimit = subJobLimit;
    }
}

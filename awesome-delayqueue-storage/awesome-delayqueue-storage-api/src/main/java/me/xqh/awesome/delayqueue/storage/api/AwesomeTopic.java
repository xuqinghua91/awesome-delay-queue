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
    private Integer capacity;
    private Integer subJobLimit;
    private Integer triggerType;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSubJobLimit() {
        return subJobLimit;
    }

    public void setSubJobLimit(Integer subJobLimit) {
        this.subJobLimit = subJobLimit;
    }

    public Integer getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }
}

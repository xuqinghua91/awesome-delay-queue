/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:ReqAddTopic.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.web.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class ReqAddTopic extends BaseRequest {
    @NotBlank(message = "topic 不能为空")
    private String topic;
    private Integer capacity;
    private Integer subJobLimit;

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
}

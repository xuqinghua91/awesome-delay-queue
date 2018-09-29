/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:ReqAddJob.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.web.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class ReqAddJob {
    @NotBlank(message = "id 不能为空")
    private String id;
    @NotBlank(message = "topic 不能为空")
    private String topic;
    @NotNull(message = "delaySeconds不能为空")
    @Min(1)
    private long delaySeconds;
    private String data;
    private int triggerType;

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
}

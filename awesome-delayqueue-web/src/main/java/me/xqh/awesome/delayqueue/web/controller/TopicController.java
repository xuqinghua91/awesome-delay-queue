/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:TopicController.java
 * Date:2018/10/08
 */

package me.xqh.awesome.delayqueue.web.controller;

import me.xqh.awesome.delayqueue.common.exception.AwesomeException;
import me.xqh.awesome.delayqueue.storage.api.AwesomeJob;
import me.xqh.awesome.delayqueue.storage.api.AwesomeTopic;
import me.xqh.awesome.delayqueue.storage.api.StorageService;
import me.xqh.awesome.delayqueue.web.request.ReqAddJob;
import me.xqh.awesome.delayqueue.web.request.ReqAddTopic;
import me.xqh.awesome.delayqueue.web.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinghua.xu
 * @date 2018/10/8
 **/
@RestController
public class TopicController extends BaseController{
    @Autowired
    private StorageService storageService;
    @RequestMapping("topic/add")
    public BaseResponse<Boolean> addJob(@RequestBody ReqAddTopic req)  {
        AwesomeTopic topic = new AwesomeTopic();
        topic.setTopic(req.getTopic());
        topic.setCapacity(req.getCapacity());
        topic.setSubJobLimit(req.getSubJobLimit());
        topic.setTriggerType(req.getTriggerType());
        boolean result = storageService.addAwesomeTopic(topic);
        return new BaseResponse<>(result);
    }
}

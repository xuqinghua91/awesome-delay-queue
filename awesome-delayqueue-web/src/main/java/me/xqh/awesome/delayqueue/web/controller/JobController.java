/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AddJobController.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.web.controller;

import me.xqh.awesome.delayqueue.common.exception.AwesomeException;
import me.xqh.awesome.delayqueue.storage.api.AwesomeJob;
import me.xqh.awesome.delayqueue.storage.api.StorageService;
import me.xqh.awesome.delayqueue.web.request.ReqAddJob;
import me.xqh.awesome.delayqueue.web.request.ReqJobCountdown;
import me.xqh.awesome.delayqueue.web.request.ReqListReady;
import me.xqh.awesome.delayqueue.web.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
@RestController
public class JobController extends BaseController{
    @Autowired
    private StorageService storageService;
    @RequestMapping("job/add")
    public BaseResponse<Boolean> addJob(@RequestBody ReqAddJob job) {
        AwesomeJob awesomeJob = new AwesomeJob(job.getId(),job.getTopic(),job.getDelaySeconds());
        awesomeJob.setData(job.getData());
        boolean result = false;
        try {
            result = storageService.addJob(awesomeJob);
        } catch (AwesomeException e) {
            return new BaseResponse<>(e.getErrorCode(),e.getErrorMsg());
        }
        return new BaseResponse<>(result);
    }

    @RequestMapping("ready/list")
    public BaseResponse<List<AwesomeJob>> listReady(@RequestBody ReqListReady req){
        List<AwesomeJob> list = storageService.consumeReadyJobs(req.getTopic(),req.getNumber());
        return new BaseResponse<>(list);
    }

    @RequestMapping("job/countdown")
    public BaseResponse<Boolean> listReady(@RequestBody ReqJobCountdown req){
        storageService.countdownJob(req.getJobId());
        return new BaseResponse<>(true);
    }
}

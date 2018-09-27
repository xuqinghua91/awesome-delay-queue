/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:StorageService.java
 * Date:2018/09/27
 */

package me.xqh.awesome.delayqueue.core.storage;

import java.util.List;

/**
 * @author qinghua.xu
 * @date 2018/9/27
 **/
public interface StorageService {
    /**
     * 添加任务
     * @param job
     */
    void addJob(AwesomeJob job);

    /**
     * 获取job信息
     * @param id
     * @return
     */
    AwesomeJob getJob(String id);

    /**
     * 删除job
     * @param id
     */
    void removeJob(String id);

    /**
     * 获取过期的job
     * @return
     */
    List<AwesomeJob> listExpiredJobs(long currentTime);

    /**
     * 将过期job或者到达计数的job转移到就绪队列
     * @param jobList
     */
    void transferExpiredJobs(List<AwesomeJob> jobList);

    /**
     * 获取对应topic下的就绪job
     * @param topic
     * @param number 获取的数量
     * @return
     */
    List<AwesomeJob> consumeReadyJobs(String topic,int number);
}

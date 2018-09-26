package me.xqh.awesome.delayqueue.core.job;

/**
 * @author xuqinghua
 * @date 2018-09-26
 */
public interface JobPoolService {
    void putJob(AwesomeJob job);
    AwesomeJob getJob(String id);
    void deleteJob(String id);
}

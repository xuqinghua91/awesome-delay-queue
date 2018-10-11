/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:PollingAwesomeExecutor.java
 * Date:2018/09/28
 */

package me.xqh.awesome.delayqueue.scheduling;

import me.xqh.awesome.delayqueue.TimeUtil;
import me.xqh.awesome.delayqueue.common.AwesomeURL;
import me.xqh.awesome.delayqueue.storage.api.AwesomeJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 *每隔一段时间轮询是否有过期job
 * @author qinghua.xu
 * @date 2018/9/28
 **/
public class PollingAwesomeExecutor extends AbstractAwesomeExecutor {
    private ExecutorService executorService ;
    private final int coreSize = Runtime.getRuntime().availableProcessors();
    private ConcurrentLinkedQueue<AwesomeJob> blockingQueue = new ConcurrentLinkedQueue<>();
    private static final int  pollTimeIntervalMills = 1000;

    private boolean shutdown;
    public PollingAwesomeExecutor(AwesomeURL url){
        super(url);
//        executorService = Executors.newFixedThreadPool(coreSize);
        executorService = new ThreadPoolExecutor(coreSize,coreSize,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("拒绝任务");
            }
        });
    }
    @Override
    public void execute() {
        while (!shutdown){
            produce();
            try {
                TimeUnit.MILLISECONDS.sleep(pollTimeIntervalMills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consume();
        }
    }

    /**
     * 生成一个获取满足条件的job，加入阻塞队列。
     */
    private void produce(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                List<AwesomeJob> list =storageService.listExpiredJobs(System.currentTimeMillis());
                for (AwesomeJob job : list){
                    blockingQueue.offer(job);
                }
            }
        };
        Runnable runCount = new Runnable() {
            @Override
            public void run() {
                List<AwesomeJob> list =storageService.listCountdownJobs();
                for (AwesomeJob job : list){
                    blockingQueue.offer(job);
                }
            }
        };
        executorService.submit(run);
        executorService.submit(runCount);
    }

    /**
     * 从阻塞队列中获取job,每个job开启一个线程提交到线程池
     */
    private void consume(){
        int size = blockingQueue.size();
        for (int i=0;i<size;i++){
            System.out.println("本次调度，队列中数量："+size);
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    AwesomeJob job = blockingQueue.poll();
                    System.out.println(Thread.currentThread().getId()+" 开始消费: "+job.getId()+" , on "+ TimeUtil.getTimeStr(new Date()));
                    List<AwesomeJob> list = new ArrayList<>(1);
                    list.add(job);
                    storageService.transferExpiredJobs(list);
                    System.out.println(Thread.currentThread().getId()+ "结束消费 consume job: "+job.getId()+" , on "+ TimeUtil.getTimeStr(new Date()));
                }
            };
            executorService.submit(run);
        }

    }

    public void shutdown(){
        shutdown = true;
    }

}

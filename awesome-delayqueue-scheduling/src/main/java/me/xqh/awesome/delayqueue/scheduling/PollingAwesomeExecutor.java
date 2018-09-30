/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:PollingAwesomeExecutor.java
 * Date:2018/09/28
 */

package me.xqh.awesome.delayqueue.scheduling;

import me.xqh.awesome.delayqueue.common.AwesomeURL;
import me.xqh.awesome.delayqueue.storage.api.AwesomeJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        while (true){
//            System.out.println("开始轮询..."+new Date(System.currentTimeMillis()));
            produce();
            consume();
            try {
                TimeUnit.MILLISECONDS.sleep(pollTimeIntervalMills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        executorService.submit(run);
    }

    /**
     * 从阻塞队列中获取job,每个job开启一个线程提交到线程池
     */
    private void consume(){
        for (int i=0;i<blockingQueue.size();i++){
            final AwesomeJob job = blockingQueue.poll();
            System.out.println("consume 取出 job: "+job.getId());
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始执行 consume job: "+job.getId()+" , "+ new Date(System.currentTimeMillis()));
                    List<AwesomeJob> list = new ArrayList<>(1);
                    list.add(job);
                    storageService.transferExpiredJobs(list);
                    System.out.println("结束执行 consume job: "+job.getId());
                }
            };
            executorService.submit(run);
        }

    }
}

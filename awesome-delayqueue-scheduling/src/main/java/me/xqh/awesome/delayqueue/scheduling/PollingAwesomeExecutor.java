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
    private LinkedBlockingQueue<AwesomeJob> blockingQueue = new LinkedBlockingQueue<>();
    private static final int  pollTimeIntervalMills = 5000;

    public PollingAwesomeExecutor(AwesomeURL url){
        super(url);
        executorService = Executors.newFixedThreadPool(coreSize);
    }
    @Override
    public void execute() {
        while (true){
            System.out.println("开始轮询...");
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
        Iterator<AwesomeJob> iterator = blockingQueue.iterator();
        while (iterator.hasNext()){
            final AwesomeJob job = iterator.next();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    List<AwesomeJob> list = new ArrayList<>(1);
                    list.add(job);
                    storageService.transferExpiredJobs(list);
                }
            };
            executorService.submit(run);
        }

    }
}

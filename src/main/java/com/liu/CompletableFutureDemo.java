package com.liu;

import java.util.concurrent.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author liushuaibiao
 * @date 2023/6/25 15:30
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //自己指定线程池
        },threadPool);
        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }
}

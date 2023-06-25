package com.liu;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liushuaibiao
 * @date 2023/6/25 10:41
 */
public class CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        
        FutureTask futureTask = new FutureTask(new MyThread2());
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();
        //获取结果
        System.out.println(futureTask.get());
    }
}
class MyThread2 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("---come in call");
        return "hello Callable";
    }
}

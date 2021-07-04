package com.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        demoThreadNotSafe();
    }

    private static void demoThreadNotSafe() {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.submit(new Incrementor());
        }

        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Incrementor.getCount());
        System.out.println("largest thread pool size:" + threadPool.getLargestPoolSize());
    }
}
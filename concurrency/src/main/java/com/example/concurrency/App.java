package com.example.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        demoThreadNotSafe();
        demoCurrencyBenefit();
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

    private static void demoCurrencyBenefit() {
        ThreadPoolExecutor basicThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        ThreadPoolExecutor extraThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<Integer> basicFuture = basicThreadPool.submit(() -> {
            // the basic task
            return 0;
        });

        Future<Double> extraFuture = extraThreadPool.submit(() -> {
            // some extra task
            return 1.0;
        });

        try {
            basicFuture.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }

        try {
            extraFuture.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Info");
        }
    }

}
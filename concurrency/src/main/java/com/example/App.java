package com.example;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.submit(new Add());
        }

        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        System.out.println("largest thread pool size:" + threadPool.getLargestPoolSize());
    }

    static class Add implements Runnable {

        @Override
        public void run() {
            // lock.lock();
            count++;
            // lock.unlock();
        }
    }
}

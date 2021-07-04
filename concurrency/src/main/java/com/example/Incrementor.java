package com.example;

public class Incrementor implements Runnable {

    private static int count = 0;

    @Override
    public void run() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}
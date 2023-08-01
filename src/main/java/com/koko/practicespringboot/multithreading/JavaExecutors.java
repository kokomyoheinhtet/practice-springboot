package com.koko.practicespringboot.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private final int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task id " + id + " is running on " + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 5);

        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SingleThreadExecutor {
    public void execute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task(i));
        }
        // need to shut it down
        executorService.shutdown();
    }
}

class FixedThreadPoolExecutor {
    public void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task(i));
        }
        // need to shut it down
        executorService.shutdown();
    }
}

public class JavaExecutors {
    public static void main(String[] args) {
//        SingleThreadExecutor singleThreadExecutor = new SingleThreadExecutor();
//        singleThreadExecutor.execute();
        FixedThreadPoolExecutor fixedThreadPoolExecutor = new FixedThreadPoolExecutor();
        fixedThreadPoolExecutor.execute();
    }

}

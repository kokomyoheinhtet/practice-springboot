package com.koko.practicespringboot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JustToRun {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Task("executorService"));
        }


//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.schedule(new Task("schedule"), 10, TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleAtFixedRate(new Task("scheduleAtFixedRate"), 5, 10, TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleWithFixedDelay(new Task("scheduleWithFixedDelay"), 5, 5, TimeUnit.SECONDS);

        System.out.println("Current Thread: " + Thread.currentThread().getName());

    }

    static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
            System.out.println("Task Name: " + name);
            System.out.println();
        }
    }
}

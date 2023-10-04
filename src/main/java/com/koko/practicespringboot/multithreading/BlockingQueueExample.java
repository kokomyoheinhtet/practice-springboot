package com.koko.practicespringboot.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;

        while (true) {
            try {
                blockingQueue.put(counter);
                System.out.println("Adding item into queue... " + counter);
                counter++;
                Thread.sleep(50);
                if (counter > 50) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondWorker implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer counter = blockingQueue.take();
                System.out.println("Taking item from queue... " + counter);
                System.out.println();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        new Thread(new FirstWorker(blockingQueue)).start();
        new Thread(new SecondWorker(blockingQueue)).start();
    }
}

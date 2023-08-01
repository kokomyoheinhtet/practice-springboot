package com.koko.practicespringboot.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections {
    public static void main(String[] args) {
//        List<Integer> nums = new ArrayList<>();
        List<Integer> nums = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                nums.add(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                nums.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size of array : " + nums.size());
    }
}

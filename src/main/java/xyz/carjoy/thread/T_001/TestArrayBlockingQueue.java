package main.java.xyz.carjoy.thread.T_001;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestArrayBlockingQueue {

    static BlockingDeque<String> strs = (BlockingDeque<String>) new ArrayBlockingQueue<String>(10);

    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
             strs.put("a"+i);
        }

//        strs.put("aaa");
//
//        strs.add("aaaa");
//
//        strs.offer("aaa");

        strs.offer("a", 1, TimeUnit.SECONDS);
    }
}

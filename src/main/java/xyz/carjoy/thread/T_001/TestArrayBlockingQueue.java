package main.java.xyz.carjoy.thread.T_001;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class TestArrayBlockingQueue {

    static BlockingDeque<String> strs = new ArrayBlockingQueue<>();

    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
             strs.put("a"+i);
        }
    }
}

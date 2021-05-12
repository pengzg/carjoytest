package main.java.xyz.carjoy.thread.T_001;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestConcurrentQueue {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 100; i++) {
            strs.offer("a"+i);
        }

        System.out.println(strs);
        System.out.println(strs.size());
        System.out.println(strs.poll());
        System.out.println(strs.size());
        System.out.println(strs.peek());
        System.out.println(strs.size());


    }
}

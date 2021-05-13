package main.java.xyz.carjoy.thread.T_001;

import java.util.PriorityQueue;

public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");
        q.add("f");
        for (int i = 0; i < 6; i++) {
            System.out.println(q.poll());
        }
    }
}

package main.java.xyz.carjoy.thread.T_001;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestQueue {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号=>"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true) {
                    String s = tickets.poll();
                    if (s == null) {
                        break;
                    }
                    System.out.println("销售了==>"+s);
                }
            }).start();
        }
    }
}

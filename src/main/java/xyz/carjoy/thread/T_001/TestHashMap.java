package main.java.xyz.carjoy.thread.T_001;

import java.util.HashMap;
import java.util.UUID;

public class TestHashMap {

    static HashMap<UUID,UUID> m = new HashMap<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREADCOUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread{
        int start;
        int gap = count/THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }
    }

    public static void main(String[] args) {
        System.out.println("main");
        
    }
}

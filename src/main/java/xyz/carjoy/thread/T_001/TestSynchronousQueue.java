package xyz.carjoy.thread.T_001;

import java.util.concurrent.SynchronousQueue;

public class TestSynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        strs.put("aaa");
//        strs.put("bbb");
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}

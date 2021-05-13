package main.java.xyz.carjoy.thread.T_001;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestLinkedBlockingQueue {
    static BlockingDeque<String> strs = new LinkedBlockingDeque<>();
    static Random r = new Random();

    public static void main(String[] args) {

       new Thread(()->{
           for (int i = 0; i < 100; i++) {
               try {
                   strs.put("a"+i);
                   TimeUnit.MICROSECONDS.sleep(r.nextInt(1000));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }


           }
       }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for(;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+"take--"+strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }

    }
}

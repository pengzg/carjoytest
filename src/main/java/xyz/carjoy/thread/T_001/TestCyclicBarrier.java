package xyz.carjoy.thread.T_001;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20,()->{
            System.out.println("人满员了，请等下一班==>");
        });

        for (int i = 0; i < 105; i++) {
             new Thread(()->{
                 try {
                     barrier.await();
                     System.out.println(Thread.currentThread().getName()+"加入 ");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (BrokenBarrierException e) {
                     e.printStackTrace();
                 }
             },"thread=>"+i).start();
        }
    }
}

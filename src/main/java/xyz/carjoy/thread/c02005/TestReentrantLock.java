package xyz.carjoy.thread.c02005;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        for (int i=0; i<10; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    reentrantLock.lock();;
                    System.out.println(Thread.currentThread().getName()+">>i "+ finalI);
                } catch (Exception e) {

                } finally {
                    if (reentrantLock != null) {
                        reentrantLock.unlock();
                    }
                }
            }).start();
        }
    }
}

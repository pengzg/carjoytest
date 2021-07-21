package xyz.carjoy.thread.threadpool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockCondition {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
              lock.lock();
              for (char c : aI) {
                  System.out.println(c);
                  condition.signal();
                  condition.await();
              }
              condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();
        new Thread(()->{
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        },"t2").start();
    }
}

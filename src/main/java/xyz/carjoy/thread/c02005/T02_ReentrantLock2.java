package main.java.xyz.carjoy.thread.c02005;

import main.java.xyz.carjoy.thread.T_001.T;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for (int i=0; i<10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("i==>"+i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2.......");
        } finally {
            lock.unlock();
        }



    }

    public static void main(String[] args) {
        T02_ReentrantLock2 rl = new T02_ReentrantLock2();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }
}

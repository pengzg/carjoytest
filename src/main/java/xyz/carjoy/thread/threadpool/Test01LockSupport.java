package xyz.carjoy.thread.threadpool;

import java.util.concurrent.locks.LockSupport;

public class Test01LockSupport {
    static Thread t1 =null,t2=null;

    public static void main(String[] args) {
        System.out.println("this is main for you");
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(()->{
            for (char c : aI) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"t1");

        t2 = new Thread(()->{
            for (char c : aC) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t2);
            }
        },"t2");

        t1.start();
        t2.start();

    }
}

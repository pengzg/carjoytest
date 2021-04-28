package main.java.xyz.carjoy.thread.c02005;

import main.java.xyz.carjoy.thread.T_001.T;
import sun.lwawt.macosx.CSystemTray;

import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock {
    synchronized void m1(){
        for (int i=0; i<10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 2) {
                m2();
            }
        }
     }

     synchronized void m2(){
        System.out.println("m2......");
     }

     public static void main(String[] args){
        T01_ReentrantLock rl = new T01_ReentrantLock();
        new Thread(rl::m1).start();

         try {
             TimeUnit.SECONDS.sleep(1);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
}

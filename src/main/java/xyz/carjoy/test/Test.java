package xyz.carjoy.test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test {
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName());
        }
    }

    static  class MyThread2 implements  Runnable{

        @Override
        public void run() {
            // todo
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class myCall implements Callable<String>{

        @Override
        public String call() throws Exception {

            System.out.println(Thread.currentThread().getName());
            return "this is t3";
        }
    }

    public static void main(String[] args) {
        //1
        new MyThread().run();
        // 2.
        new Thread(new MyThread2(), "2Run").start();

        Thread t3 = new Thread(new FutureTask<String>(new myCall()));
        t3.start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }, "4thread").start();
    }

}

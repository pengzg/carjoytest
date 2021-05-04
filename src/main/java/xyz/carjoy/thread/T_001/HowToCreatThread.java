package main.java.xyz.carjoy.thread.T_001;

import javax.swing.text.html.StyleSheet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class HowToCreatThread {

    static class MyThread extends Thread{

        @Override
        public void run(){
            System.out.println("this is "+ Thread.currentThread().getName());
        }
    }

    static class MyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("this is "+Thread.currentThread().getName());
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("this is MyCall");
            return "Success";
        }
    }

    public static void main(String[] args) {
//        01
        new MyThread().start();
//02
        new Thread(new MyRun(),"myRun").start();

// 03

        Thread t = new Thread(new FutureTask<String>(new MyCall()));
        t.start();
//04
        new Thread(()->{
            System.out.println("use lambda to creat a thread "+Thread.currentThread().getName());
        },"04thread").start();

// 05
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("Hello ThreadPool");
        });

        service.shutdown();


    }

}

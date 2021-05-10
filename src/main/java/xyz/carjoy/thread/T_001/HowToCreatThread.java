package main.java.xyz.carjoy.thread.T_001;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class HowToCreatThread {
    // 类继承Mythread
    static class MyThread extends Thread{

        
        @Override
        public void run(){
            System.out.println("this is "+ Thread.currentThread().getName());
        }
    }
      // 类实现Runnable接口
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

        // 创建100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(new MyRun(),"thread"+i).start();

            new Thread(()->{
                System.out.println("use lambda to creat a thread "+Thread.currentThread().getName());
            },"04thread---"+i).start();
            
        }



    }

}

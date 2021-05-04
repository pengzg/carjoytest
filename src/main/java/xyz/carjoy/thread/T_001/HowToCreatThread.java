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
            System.out.println("this is MyThread");
        }
    }

    static class MyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("this is myRun ");
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
        new MyThread().start();

        new Thread(new MyRun()).start();



        Thread t = new Thread(new FutureTask<String>(new MyCall()));
        t.start();

        new Thread(()->{
            System.out.println("use lambda to creat a thread");
        }).start();


        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("Hello ThreadPool");
        });

        service.shutdown();


    }

}

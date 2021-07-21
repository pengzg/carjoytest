package xyz.carjoy.thread.threadpool;

import sun.jvm.hotspot.oops.TypeArrayKlass;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MICROSECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();
        System.out.println(task.get());
    }
}

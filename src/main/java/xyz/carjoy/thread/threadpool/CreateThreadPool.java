package xyz.carjoy.thread.threadpool;

import java.util.concurrent.*;

public class CreateThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);






    }
}

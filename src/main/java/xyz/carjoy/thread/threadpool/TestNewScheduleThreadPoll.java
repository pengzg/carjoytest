package xyz.carjoy.thread.threadpool;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author liuchj
 * @version 1.0
 * @className ScheduleThreadTest
 * @description //TODO
 * @date 2019/5/30
 **/
public class TestNewScheduleThreadPoll {
    /**
     * 线程安全的队列
     */
    static Queue<String> queue = new ConcurrentLinkedQueue<String>();



    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < queue.size(); i++) {
            System.out.println(i);
            String value = queue.poll();
            ScheduledFuture<String> scheduledFuture = executorService.schedule(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if (value != "" && null != value) {
                        System.out.println(value);
                        System.out.println("时间:" + sdf.format(new Date())+"线程" + Thread.currentThread().getName() + " 执行了task: " + value);
                    }
                    return "call";
                }
            }, 3, TimeUnit.SECONDS);

            System.out.println(scheduledFuture.get());
            continue;
        }

        executorService.shutdown();
    }

    static {
        queue.add("backStockList");
        queue.add("updateTicketState");
    }
}

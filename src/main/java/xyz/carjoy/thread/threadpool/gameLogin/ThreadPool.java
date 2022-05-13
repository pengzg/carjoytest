package xyz.carjoy.thread.threadpool.gameLogin;

public class ThreadPool {
    ThreadItem[] threads;

    public ThreadPool(int count, TaskQueue taskQueue){
        threads = new ThreadItem[count];

        for (int i = 0; i <count; i++) {
            threads[i] = new ThreadItem(taskQueue);
            threads[i].start();
        }
    }
}

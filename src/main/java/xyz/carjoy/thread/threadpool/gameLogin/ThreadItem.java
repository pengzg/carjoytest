package xyz.carjoy.thread.threadpool.gameLogin;

public class ThreadItem extends Thread{

    TaskQueue queue;

    public ThreadItem(TaskQueue queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        while (true) {
            Runnable task = queue.getTask();
            if (task != null) {
                task.run();
            }
        }

    }
}

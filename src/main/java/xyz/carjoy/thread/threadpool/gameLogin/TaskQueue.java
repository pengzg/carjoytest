package xyz.carjoy.thread.threadpool.gameLogin;

import java.util.ArrayList;
import java.util.List;

public class TaskQueue {

    private List<Runnable> tasks = new ArrayList<Runnable>();

    public synchronized void add(Runnable task) {
        tasks.add(task);
        this.notify();
    }

    public synchronized Runnable getTask()  {
        if (tasks.size()>0 ) {
            return tasks.remove(0);
        } else {
            try {
                this.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}

package xyz.carjoy.thread.threadpool.gameLogin;

import java.util.Queue;

public class GameServer {
    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();

        ThreadPool threadPool = new ThreadPool(5, taskQueue);

        for (int i = 0; i < 200; i++) {
            UserLoginTask userLoginTask = new UserLoginTask();
            userLoginTask.setName("user"+i);
            taskQueue.add(userLoginTask);
        }

    }
}

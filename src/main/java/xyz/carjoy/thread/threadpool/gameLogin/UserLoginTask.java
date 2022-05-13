package xyz.carjoy.thread.threadpool.gameLogin;

public class UserLoginTask implements Runnable{

    String name;

    public void setName(String name){
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"==>"+name);
    }
}

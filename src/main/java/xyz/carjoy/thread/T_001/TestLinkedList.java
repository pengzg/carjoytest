package xyz.carjoy.thread.T_001;

import sun.plugin2.main.server.WindowsHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestLinkedList {
    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号=>"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true) {
                   synchronized (tickets) {
                       if (tickets.size()<=0) break;
                       try {
                           TimeUnit.MILLISECONDS.sleep(10);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }

                       System.out.println("销售了==>"+tickets.remove(0));
                   }
                }
            }).start();
        }
    }
}

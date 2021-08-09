package xyz.carjoy.thread.T_001;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TestTicketSeller2{
    static Vector<String> tickets = new Vector<String>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号==>"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size()>0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了==>"+tickets.remove(0));
                }
            }).start();
        }
    }
}


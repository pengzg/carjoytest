package main.java.xyz.carjoy.thread.threadpool;

public class TestCAS {
    enum ReadyToRun{T1,T2};
    static volatile ReadyToRun r = ReadyToRun.T1;
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();


        new Thread(()->{
            for (char c: aI) {
                while (r != ReadyToRun.T1) {
                    System.out.println("当前运行的是t2");
                }
                System.out.println(c);
                r = ReadyToRun.T2;

            }
        },"t1").start();
        new Thread(()->{
            for (char c : aC) {
                while (r != ReadyToRun.T2) {
                    System.out.println("当前运行的是t1");
                }
                System.out.println(c);
                r = ReadyToRun.T1;


            }
        },"t2").start();
    }
}

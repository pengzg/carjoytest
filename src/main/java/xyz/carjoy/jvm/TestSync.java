package main.java.xyz.carjoy.jvm;

public class TestSync {
    synchronized void m(){

    }
    void n(){
        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {

    }
}

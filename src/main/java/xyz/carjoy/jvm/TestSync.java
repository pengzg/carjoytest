package main.java.xyz.carjoy.jvm;

import java.util.HashMap;

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
        HashMap<String,String> map = new HashMap<>();
        System.out.println(System.identityHashCode(map));
    }
}

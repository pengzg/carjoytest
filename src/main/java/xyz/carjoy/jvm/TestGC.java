package main.java.xyz.carjoy.jvm;

import java.util.LinkedList;
import java.util.List;

public class TestGC {
    public static void main(String[] args) {
        System.out.println("hellp gc");
        List list = new LinkedList();
        for (;;) {
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }
}

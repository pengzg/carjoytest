package xyz.carjoy.jvm;

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

        String a = "Aa";
        String b = "BB";

        int aa = a.hashCode();
        int bb = b.hashCode();
        System.out.println(aa);
        System.out.println(bb);
        HashMap<String,String> map = new HashMap<>();
        map.put("a", "string");
        map.put(a, "this is aa");
        map.put(b, "this is BB");


        System.out.println(System.identityHashCode(map));
        System.out.println(map.hashCode());
        System.out.println(map.get(a));
        System.out.println(map.get(b));




    }
}

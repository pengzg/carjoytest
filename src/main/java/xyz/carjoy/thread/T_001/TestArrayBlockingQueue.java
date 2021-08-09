package xyz.carjoy.thread.T_001;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestArrayBlockingQueue {

    static ArrayBlockingQueue<String> strs = new ArrayBlockingQueue<String>(10);

    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
             strs.put("a"+i);
        }

        System.out.println(strs);
        // 如果上面是9循环 则不阻塞  如果是10则是阻塞
//        strs.put("aaa");

//       如果上面是9 则正常 否则 异常
//        strs.add("aaaa");
//     如果上面是9个 则能加入    如果是10  不改变
        strs.offer("aaaa");

//        strs.offer("a", 1, TimeUnit.SECONDS);
        System.out.println(strs);
    }
}

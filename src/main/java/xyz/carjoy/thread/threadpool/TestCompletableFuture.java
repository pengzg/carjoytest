package main.java.xyz.carjoy.thread.threadpool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TestCompletableFuture {

    public static void main(String[] args) {

        long start,end;
       // end = System.currentTimeMillis();

       // System.out.println("use serial method call"+ (end-start));

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceofTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceofTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceofJD());

        CompletableFuture.allOf(futureJD,futureTB,futureTM).join();
        CompletableFuture.supplyAsync(()->priceofJD()).thenApply(String::valueOf).thenApply(str->"price "+str).thenAccept(System.out::println);

        end = System.currentTimeMillis();

        System.out.println("user completable future "+ (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static double priceofTM(){
        delay(500);
        return 1.00;
    }
    private static double priceofTB(){
        delay(750);
        return 2.00;
    }
    private static double priceofJD(){
        delay(800);
        return 3.00;
    }

    private static void delay(int s){
        int time = new Random().nextInt(s);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep ", time+"");
    }
}

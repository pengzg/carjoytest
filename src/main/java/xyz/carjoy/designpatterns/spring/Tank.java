package xyz.carjoy.designpatterns.spring;

import java.util.Random;

public class Tank {
    public void move(){
        System.out.println("Tank is moving clacla.........");

        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

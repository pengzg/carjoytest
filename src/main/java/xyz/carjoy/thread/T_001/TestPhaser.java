package xyz.carjoy.thread.T_001;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class TestPhaser {

    static Random r = new Random();

    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
           new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
        
    }

    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase,int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人到齐了！==》"+registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了了！==》"+registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！==》"+registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("结束了！==》"+registeredParties);
                    System.out.println();
                    return false;
                default:
                    return true;

            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
           this.name = name;
        }

        public void arrive(){
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 到达现场");
            phaser.arriveAndAwaitAdvance();

        }
        public void eat(){
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 现场吃饭");
            phaser.arriveAndAwaitAdvance();

        }
        public void leave(){
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 离开现场");
            phaser.arriveAndAwaitAdvance();

        }

        private void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.println(name+"洞房");
                phaser.arriveAndAwaitAdvance();
            }  else {
                phaser.arriveAndAwaitAdvance();
            }
        }

        @Override
        public void run(){
            arrive();
            eat();
            leave();
            hug();
        }
    }
}

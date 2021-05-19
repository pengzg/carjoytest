package main.java.xyz.carjoy.jvm;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("main method");
        System.out.println(String.class.getClassLoader());
        System.out.println(TestMain.class.getClassLoader());
    }

}

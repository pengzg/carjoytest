package main.java.xyz.carjoy.db;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("main.java.xyz.carjoy.db.Person");


        System.out.println(aClass.getDeclaredFields());
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method:methods) {
            System.out.println(method.getName());
        }
    }

}

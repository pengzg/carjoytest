package xyz.carjoy.db;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("xyz.carjoy.db.Person");


        System.out.println(aClass.getDeclaredFields());
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method:methods) {
            System.out.println(method.getName());
        }

        Class<?> sClass = Class.forName("xyz.carjoy.db.Student");


        System.out.println(sClass.getDeclaredFields());
        Method[] sMethods = sClass.getDeclaredMethods();
        for (Method method:sMethods) {
            System.out.println(method.getName());
        }
    }

}

package xyz.carjoy.test;

public class TestEqual {
    public static void main(String[] args) {
        User user1 = new User(12, "kit");
        User user2 = new User(12, "kit");

        System.out.println(user1.equals(user2));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        // 写了hashcode
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
    }
}

package xyz.carjoy.jvm;

public class TestStack {
    public static void main(String[] args) {
        int i = 8;

        int j = 200;

        int k = 2000000000;

        TestStack ts = new TestStack();
        ts.m(i);


    }

    public int m(int n) {
        if (n == 1) return 1;
        return n*m(n-1);
    }
}

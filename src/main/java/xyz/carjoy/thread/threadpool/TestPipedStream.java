package main.java.xyz.carjoy.thread.threadpool;

import sun.tools.tree.ByteExpression;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class TestPipedStream {

    public static void main(String[] args) throws IOException {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();

        PipedOutputStream out1 = new PipedOutputStream();
        PipedOutputStream out2 = new PipedOutputStream();

        input1.connect(out2);
        input2.connect(out1);

        String msg = "your turn";

        new Thread(()->{

            byte[] buffer = new byte[9];
            try {
                for (char c : aI) {
                    System.out.println(c);
                    input1.read(buffer);
                    if (new String(buffer).equals("msg")) {
                        System.out.println(c);
                    }
                    out1.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"t1").start();

        new Thread(()->{
            byte[] buffer = new byte[9];
            try {
                for (char c : aC) {
                    System.out.println(c);
                    out2.write(msg.getBytes());
                    input2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"t2").start();
    }
}

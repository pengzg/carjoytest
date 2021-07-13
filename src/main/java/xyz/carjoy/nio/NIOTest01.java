package main.java.xyz.carjoy.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class NIOTest01 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());
        System.out.println("mark:"+buffer);
        buffer.put("123".getBytes());
    }
}

package main.java.xyz.carjoy.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class C10Kcclient {

    public static void main(String[] args)  throws Exception{
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress address = new InetSocketAddress("", 443);
        for (int i = 10000; i < 65000; i++) {
            try {
                SocketChannel client1 = SocketChannel.open();
                SocketChannel client2 = SocketChannel.open();
                client1.bind(new InetSocketAddress("", i));
                client1.connect(address);
                boolean c1 = client1.isOpen();
                clients.add(client1);
                client2.bind(new InetSocketAddress("", i));
                client2.connect(address);
                boolean c2 = client2.isOpen();
                clients.add(client2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("clients:"+clients.size());
    }
}

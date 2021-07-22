package xyz.carjoy.rocketmq.rmq01;

import org.apache.activemq.transport.stomp.Stomp;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;


public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("testtopic");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
        producer.start();
       // topic消息的目的地  和message绑定
        // 单条发送
//        Message msg = new Message("test0002","tes6666t".getBytes());
//        SendResult send = producer.send(msg);
        // 多条发送
        ArrayList<Message> list = new ArrayList<>();
        Message msg1 = new Message("test0001","test 第一条".getBytes());
        Message msg2 = new Message("test0001","test 第二条".getBytes());
        Message msg3 = new Message("test0001","test 第三条".getBytes());
        Message msg4 = new Message("test0001","test 第四条".getBytes());
        list.add(msg1);
        list.add(msg2);
        list.add(msg3);
        list.add(msg4);
        SendResult send = producer.send(list);
        System.out.println(send);
        producer.shutdown();

    }
}

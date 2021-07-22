package xyz.carjoy.rocketmq.rmq01;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;


public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("testtopic");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
        producer.start();
       // topic消息的目的地  和message绑定
        Message msg = new Message("test0002","tes6666t".getBytes());

        SendResult send = producer.send(msg);
        System.out.println(send.getSendStatus());
        producer.shutdown();

    }
}

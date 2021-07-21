package xyz.carjoy.rocketmq.rmq01;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Collection;

public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("testGroup");
        // 设置nameserver地址
        defaultMQProducer.setNamesrvAddr("42.192.16.23:9876");
        defaultMQProducer.start();
       // topic消息的目的地  和message绑定
        Message msg = new Message("testGroup", );

        defaultMQProducer.send(msg);

    }
}

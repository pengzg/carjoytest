package xyz.carjoy.rocketmq.rmq10;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;


public class Producer10 {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("testtopic10");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
        producer.start();
       // topic消息的目的地  和message绑定
        // 单条发送
        Message msg = new Message("test00010","tes6666tproduce10".getBytes());

        // 多条发送
        MessageQueue msg1 = new MessageQueue("test00010","broker-a", 3);



        producer.send(msg, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                System.out.println("list.size()==>"+list.size());
                return list.get((Integer)o);
            }
        },3);


    }
}

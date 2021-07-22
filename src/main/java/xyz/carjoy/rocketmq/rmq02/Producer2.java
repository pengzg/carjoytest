package xyz.carjoy.rocketmq.rmq02;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.cache.Cache;

import java.util.ArrayList;


public class Producer2 {
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
        Message msg1 = new Message("test0003","test 第一条".getBytes());

        list.add(msg1);

        producer.send(list, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功"+sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送失败"+throwable);
            }
        });


    }
}

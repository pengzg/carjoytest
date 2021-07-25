package xyz.carjoy.rocketmq.rmq08;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;


public class Producer8 {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("group08");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
        producer.start();
       // topic消息的目的地  和message绑定
        // 单条发送
        Message msg = new Message("test0008","producer8".getBytes());
//        SendResult send = producer.send(msg);
        // 多条发送
        // tag 用来过滤消息分组
//        Message msg = null;
//
//        for (int i = 0; i < 100; i++) {
//            msg = new Message("test0007","TAG-B","KEY-A",("test 第"+i+"条").getBytes());
//            msg.putUserProperty("age", String.valueOf(18+i));
//            producer.send(msg);
//        }


    producer.send(msg);


        System.out.println("停止");
    }
}

package xyz.carjoy.rocketmq.rmq05;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;


public class Producer5 {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("group05");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
        producer.start();
       // topic消息的目的地  和message绑定
        // 单条发送
//        Message msg = new Message("test0002","tes6666t".getBytes());
//        SendResult send = producer.send(msg);
        // 多条发送
        // tag 用来过滤消息分组
        Message msg1 = new Message("test0005","TAG-B","KEY-A","test 第X条".getBytes());


        producer.sendOneway(msg1);

        System.out.println("停止");
    }
}

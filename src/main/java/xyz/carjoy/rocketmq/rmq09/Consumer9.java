package xyz.carjoy.rocketmq.rmq09;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer9 {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer testConsumer = new DefaultMQPushConsumer("group09");
        //设置服务器
        testConsumer.setNamesrvAddr("42.192.16.23:9876");


        //每个consumer只能关注一个topic
        testConsumer.subscribe("test0009", "*");
        /**
         *   MessageListenerOrderly  一个queue开启一个线程 多个queue开启多个线程
         */
        // 最大线程数
//        testConsumer.setConsumeThreadMax(3);
        // 最小线程数
//        testConsumer.setConsumeThreadMin(1);
        testConsumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg: list
                ) {
                    System.out.println(new String(msg.getBody()) );
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        // 设置广播模式
//        testConsumer.setMessageModel(MessageModel.BROADCASTING);
        testConsumer.start();

        // 集群代表一组消费者 有一个消费者消费就行
        //广播发给每一个consumer 都被消费

        System.out.println("testConsumer-->start.......");
        
    }
}

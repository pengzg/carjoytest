package xyz.carjoy.rocketmq.rmq02;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer2 {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer testConsumer = new DefaultMQPushConsumer("testConsumer");
        //设置服务器
        testConsumer.setNamesrvAddr("42.192.16.23:9876");
        //每个consumer只能关注一个topic
        testConsumer.subscribe("test0002", "*");
        testConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg: list
                     ) {
                    System.out.println(new String(msg.getBody()) );
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        testConsumer.start();
        System.out.println("testConsumer-->start.......");
        
    }
}

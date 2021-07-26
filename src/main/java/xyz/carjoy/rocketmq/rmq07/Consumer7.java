package xyz.carjoy.rocketmq.rmq07;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer7 {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer testConsumer = new DefaultMQPushConsumer("group07");
        //设置服务器
        testConsumer.setNamesrvAddr("42.192.16.23:9876");
        // select  需要在broker.conf中加  enablePropertyFilter=true
//        MessageSelector messageSelector = MessageSelector.bySql("age >=18 and age<=28");

        //每个consumer只能关注一个topic
        testConsumer.subscribe("test0007", "*");
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
        // 设置广播模式
//        testConsumer.setMessageModel(MessageModel.BROADCASTING);
        testConsumer.start();

        // 集群代表一组消费者 有一个消费者消费就行
        //广播发给每一个consumer 都被消费

        System.out.println("testConsumer-->start.......");
        
    }
}

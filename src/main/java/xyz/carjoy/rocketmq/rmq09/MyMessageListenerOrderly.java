package xyz.carjoy.rocketmq.rmq09;

import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class MyMessageListenerOrderly implements MessageListenerOrderly {
    @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
//                System.out.println("CurrentThread==>"+Thread.currentThread().getName()+";queueid:");
                for (MessageExt msg: list) {
                    System.out.println(new String(msg.getBody()) + "CurrentThread==>"+Thread.currentThread().getName()+";queueid:"+msg.getQueueId() );
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
}

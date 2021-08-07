package xyz.carjoy.rocketmq.rmq09;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;


public class Producer9 {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("group09");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
       
//        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
        producer.start();
       // topic消息的目的地  和message绑定
        // 单条发送
//        Message msg = new Message("test0009","producer9".getBytes());
//        SendResult send = producer.send(msg);
        // 多条发送
        // tag 用来过滤消息分组
//        Message msg = null;
//        for (int i = 0; i < 100; i++) {
//            msg = new Message("test0009","TAG-B","KEY-A",("test-producer9 第"+i+"条").getBytes());
//            msg.putUserProperty("age", String.valueOf(18+i));
//            producer.send(msg);
//        }


        for (int i = 0; i < 20; i++) {
            Message msg = new Message("test0009",("producer9-01-"+i).getBytes());
            producer.send(msg, new MessageQueueSelector() {
//            手动选择一个queue
                /**
                 *
                 * @param list
                 * @param message
                 * @param o 对应的arg
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 向固定的一个queue里面写消息
//                    return list.get((Integer)(o));
                    MessageQueue queue = list.get(1);
                    System.out.println(queue.toString());
                    return queue;
                }
            }, 1, 3000);
        }

        for (int i = 0; i < 20; i++) {
            Message msg = new Message("test0009",("producer9-02-"+i).getBytes());
            producer.send(msg, new MessageQueueSelector() {
//            手动选择一个queue
                /**
                 *
                 * @param list
                 * @param message
                 * @param o 对应的arg
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 向固定的一个queue里面写消息
//                    return list.get((Integer)(o));
                    MessageQueue queue = list.get(2);
                    System.out.println(queue.toString());
                    return queue;
                }
            }, 2, 3000);
        }

        for (int i = 0; i < 20; i++) {
            Message msg = new Message("test0009",("producer9-03-"+i).getBytes());
            producer.send(msg, new MessageQueueSelector() {
//            手动选择一个queue
                /**
                 *
                 * @param list
                 * @param message
                 * @param o 对应的arg
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 向固定的一个queue里面写消息
//                    return list.get((Integer)(o));
                    MessageQueue queue = list.get(3);
                    System.out.println(queue.toString());
                    return queue;
                }
            }, 3, 3000);
        }


        for (int i = 0; i < 20; i++) {
            Message msg = new Message("test0009",("producer9-00-"+i).getBytes());
            producer.send(msg, new MessageQueueSelector() {
//            手动选择一个queue
                /**
                 *
                 * @param list
                 * @param message
                 * @param o 对应的arg
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 向固定的一个queue里面写消息
//                    return list.get((Integer)(o));
                    MessageQueue queue = list.get(0);
                    System.out.println(queue.toString());
                    return queue;
                }
            }, 0, 3000);
        }


        System.out.println("停止");
    }
}

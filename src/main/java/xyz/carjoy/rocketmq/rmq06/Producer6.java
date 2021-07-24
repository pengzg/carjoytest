package xyz.carjoy.rocketmq.rmq06;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;


public class Producer6 {
    public static void main(String[] args) throws Exception{
        TransactionMQProducer producer = new TransactionMQProducer("group06");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                // 执行本地事务
                System.out.println("executeLocalTransaction====>");
                System.out.println("msg："+new String(message.getBody()));
                System.out.println("msgtransid: "+ message.getTransactionId());

                // 事务执行成功
//                return LocalTransactionState.COMMIT_MESSAGE;
                  // 等会
                return LocalTransactionState.UNKNOW;
                  // 回滚
//                return LocalTransactionState.ROLLBACK_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt message) {
                // broker回调检查事务
                System.out.println("checkLocalTransaction====>");
                System.out.println("msg："+new String(message.getBody()));
                System.out.println("msgtransid: "+ message.getTransactionId());
                System.out.println("msgid:"+message.getMsgId());
                // 事务执行成功
                return LocalTransactionState.COMMIT_MESSAGE;
                // 等会
//                return LocalTransactionState.UNKNOW;
                // 回滚
//                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });
        producer.start();
       // topic消息的目的地  和message绑定
        // 单条发送
        Message msg = new Message("test0006","tes6666t".getBytes());
        TransactionSendResult send = producer.sendMessageInTransaction(msg,null);
        System.out.println("sendResult=>"+send);
        // 多条发送
        // tag 用来过滤消息分组
//        Message msg = null;
//
//        for (int i = 0; i < 1; i++) {
//            msg = new Message("test0006","TAG-B","KEY-A",("test 第"+i+"条").getBytes());
//            msg.putUserProperty("age", String.valueOf(18+i));
//            producer.send(msg);
//        }
        

//        System.out.println("停止");
    }
}

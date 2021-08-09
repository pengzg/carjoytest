package xyz.carjoy.kafka.kafka06;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class KafkaProducer06 {
    public static void main(String[] args) throws Exception {

        // 创建生产者
        Properties props = new Properties();

        // 这里可以配置一个或多个broker地址，会自动从broker去拉取元数据进行缓存
        props.put("bootstrap.servers", "CentOSA:9092,CentOSB:9092,CentOSC:9092");
        // 这里是把发送的key从字符串序列化为字节数组
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 这里是把发送的message从字符串序列化为字节数组
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 创建一个Producer实例：线程资源，跟各个broker建立socket连接资源
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        //ProducerRecord<String, String> record = new ProducerRecord<>(
        //        "test", "message1");

        /**
         *
         * 如果发送消息，消息不指定key，那么我们发送的这些消息，会被轮训的发送到不同的分区。
         * 如果指定了key。发送消息的时候，客户端会根据这个key计算出来一个hash值，
         * 根据这个hash值会把消息发送到对应的分区里面。
         */

        //kafka发送数据有两种方式：
        //1:异步的方式。
        // 这是异步发送的模式
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(
                    "test","key"+i, "message2"+i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        // 消息发送成功
                        System.out.println("消息发送成功");
                    } else {
                        // 消息发送失败，需要重新发送
                    }
                }

            });
        }


        Thread.sleep(10 * 1000);

        //第二种方式：这是同步发送的模式
//      producer.send(record).get();
        // 你要一直等待人家后续一系列的步骤都做完，发送消息之后
        // 有了消息的回应返回给你，你这个方法才会退出来

        producer.close();
    }
}



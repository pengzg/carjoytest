package xyz.carjoy.kafka.kafka06;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumer06 {
    public static void main(String[] args) {

        // 第一步：创建消费者
        Properties props = new Properties();

        // 这里可以配置一个或多个broker地址，会自动从broker去拉取元数据进行缓存
        props.put("bootstrap.servers", "CentOSA:9092,CentOSB:9092,CentOSC:9092");
        // 指定消费组的id
        String groupId = "testtest";
        props.put("group.id", groupId);
        // 这里是把发送的key从字节数组反序列化为字符串
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 这里是把发送的message从字节数组反序列化为字符串
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        String topicName = "test";
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 第二步：指定消费者主题
        // 一个消费者可以同时消费多个主题
        consumer.subscribe(Arrays.asList(topicName));

        try {
            while (true) {
                // 第三步：去服务器消费数据
                ConsumerRecords<String, String> records = consumer.poll(1000);// 超时时间
                // 第四步：对数据进行处理
                // 业务逻辑操作
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.offset() + ", " + record.key() + ", " + record.value());
                }
            }
        } catch (Exception e) {

        }


    }
}


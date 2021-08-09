package xyz.carjoy.kafka.kafka04;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * kafka 分区管理
 */
public class KafkaConsumer04Test {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"g2");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);

        List<TopicPartition> partitions = Arrays.asList(new TopicPartition("topic01", 0));
        // 手动指定分区  失去组管理特性
        kafkaConsumer.assign(partitions);
        // 设置消费的偏移量
//        kafkaConsumer.seekToBeginning(partitions);
        // 从topic01的第0个分区的第2个开始消费
        kafkaConsumer.seek(new TopicPartition("topic01",0), 1);
        
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            if (!consumerRecords.isEmpty()) {
                Iterator<ConsumerRecord<String, String>> recordsIterator = consumerRecords.iterator();
                while (recordsIterator.hasNext()) {
                    ConsumerRecord<String, String> record = recordsIterator.next();
                    System.out.println("topic=>"+record.topic()+",partition=>"+record.partition()+",offset=>"+record.offset()+",key=>"+record.key()+",value=>"+record.value()+",timestamp=>"+record.timestamp());
                }
            }
        }

    }
}

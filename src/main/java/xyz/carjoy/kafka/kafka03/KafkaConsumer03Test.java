package xyz.carjoy.kafka.kafka03;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class KafkaConsumer03Test {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"g1");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        // 指定分区
        List<TopicPartition> partions = Arrays.asList(new TopicPartition("topic05", 0));
        kafkaConsumer.assign(partions);
        kafkaConsumer.seekToBeginning(partions);

        kafkaConsumer.seek(new TopicPartition("topic05",0), 1);


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

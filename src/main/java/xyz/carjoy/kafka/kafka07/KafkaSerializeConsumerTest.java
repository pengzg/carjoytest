package xyz.carjoy.kafka.kafka07;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

public class KafkaSerializeConsumerTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,UserDefineDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"g2");
        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer<String, String>(props);

//        kafkaConsumer.subscribe(Pattern.compile("^topic.*"));
        kafkaConsumer.subscribe(Pattern.compile("topicuser"));

        while (true) {
            ConsumerRecords<String, User> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            if (!consumerRecords.isEmpty()) {
                Iterator<ConsumerRecord<String, User>> recordsIterator = consumerRecords.iterator();
                while (recordsIterator.hasNext()) {
                    ConsumerRecord<String, User> record = recordsIterator.next();
                    System.out.println("topic=>"+record.topic()+",partition=>"+record.partition()+",offset=>"+record.offset()+",key=>"+record.key()+",timestamp=>"+record.timestamp());
                    User userVO = record.value();
                    System.out.println(userVO.toString());

                }
            }
        }
    }
}

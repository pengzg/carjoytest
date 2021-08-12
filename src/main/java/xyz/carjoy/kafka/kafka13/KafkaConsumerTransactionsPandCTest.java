package xyz.carjoy.kafka.kafka13;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

public class KafkaConsumerTransactionsPandCTest {
    public static void main(String[] args) {
        KafkaConsumer<String, String> kafkaConsumer = getKafkaConsumer("g13");
        kafkaConsumer.subscribe(Pattern.compile("topic13"));

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

    private static KafkaConsumer<String, String> getKafkaConsumer(String groupid) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,groupid);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG,"read_committed");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, UserDefineConsumerInterceptors.class.getName());
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        return kafkaConsumer;
    }
}

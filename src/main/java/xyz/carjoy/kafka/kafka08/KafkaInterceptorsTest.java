package xyz.carjoy.kafka.kafka08;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import xyz.carjoy.interceptor.UserDefineProducerInterceptors;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaInterceptorsTest {
    public static void main(String[] args) {
        // 创建kafka producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, UserDefineProducerInterceptors.class.getName());
        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<String, User>(props);
        for (int i = 0; i < 30; i++) {
            ProducerRecord<String, User> record = new ProducerRecord<String, User>("topicuser", "key" + i, new User(i,"xiao"+i,20+i));
            Future<RecordMetadata> send = kafkaProducer.send(record);
            System.out.println(send.toString());
            
        }
        kafkaProducer.close();

    }
}

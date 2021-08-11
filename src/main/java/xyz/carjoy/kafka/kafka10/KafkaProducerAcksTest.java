package xyz.carjoy.kafka.kafka10;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerAcksTest {
    public static void main(String[] args) {
        // 创建kafka producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, UserDefineProducerInterceptors.class.getName());
        
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        
        for (int i = 0; i < 30; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic09", "key" + i, "val" + i);
            Future<RecordMetadata> send = kafkaProducer.send(record);
            System.out.println(send.toString());

        }
        kafkaProducer.close();

    }
}

package xyz.carjoy.kafka.kafka12;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerIdempotenceTest {
    public static void main(String[] args) {
        // 创建kafka producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 3次放弃
        props.put(ProducerConfig.RETRIES_CONFIG, 3);

        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 1);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        
//        for (int i = 0; i < 30; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic11", "ack", "test Idempotence");
            Future<RecordMetadata> send = kafkaProducer.send(record);
            kafkaProducer.flush();
            System.out.println(send.toString());

//        }
        kafkaProducer.close();

    }
}

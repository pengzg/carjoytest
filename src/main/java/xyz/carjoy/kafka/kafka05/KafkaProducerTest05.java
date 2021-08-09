package xyz.carjoy.kafka.kafka05;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerTest05 {
    public static void main(String[] args) {
        // 创建kafka producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "") ;
        //        AdminClient adminClient = KafkaAdminClient.create(props);
        KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> topic01 = new ProducerRecord<>("topic01", "key" + i, "val" + i);
            stringStringKafkaProducer.send(topic01);


        }
        stringStringKafkaProducer.close();
    }
}

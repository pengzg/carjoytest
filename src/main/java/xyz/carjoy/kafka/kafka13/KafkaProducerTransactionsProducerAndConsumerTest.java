package xyz.carjoy.kafka.kafka13;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

public class KafkaProducerTransactionsProducerAndConsumerTest {
    public static void main(String[] args) {

        KafkaProducer<String, String> kafkaProducer = getKafkaProducer();

        KafkaConsumer<String, String> kafkaConsumer = getKafkaConsumer("g13");

        kafkaConsumer.subscribe(Pattern.compile("topic13"));

        kafkaProducer.initTransactions();

        while (true)  {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            if (!consumerRecords.isEmpty()) {
                Iterator<ConsumerRecord<String, String>> recordsIterator = consumerRecords.iterator();

                kafkaProducer.beginTransaction();

                while (recordsIterator.hasNext()) {
                    ConsumerRecord<String, String> record = recordsIterator.next();
                    System.out.println("topic=>"+record.topic()+",partition=>"+record.partition()+",offset=>"+record.offset()+",key=>"+record.key()+",value=>"+record.value()+",timestamp=>"+record.timestamp());

                }
            }
        }

        try {
            kafkaProducer.beginTransaction();
            for (int i = 0; i < 10; i++) {

                if (i==8) {
                    int j = 10/0;
                }
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic13", "tranactions P and c ", "error data"+i);
                Future<RecordMetadata> send = kafkaProducer.send(record);
                kafkaProducer.flush();
                System.out.println(send.toString());

            }
            kafkaProducer.commitTransaction();
        } catch ( Exception e) {
            System.out.println("出错了  :"+e.getMessage());
            kafkaProducer.abortTransaction();
        } finally {

        }

        kafkaProducer.close();

    }

    private static KafkaProducer<String, String> getKafkaProducer() {
        // 创建kafka producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOSA:9092,CentOSB:9092,CentOSC:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());



        // 3次放弃
//        props.put(ProducerConfig.RETRIES_CONFIG, 3);



//        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        // 配置事务id
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction-id"+ UUID.randomUUID().toString());
        //
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,1024);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);

        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 20000);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        return kafkaProducer;
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

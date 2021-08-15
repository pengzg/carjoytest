package xyz.carjoy.kafka.kafka12;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;

public class KafkaProducerTransactionsProducerOnlyTest {
    public static void main(String[] args) {

        KafkaProducer<String, String> kafkaProducer = getKafkaProducer();
        kafkaProducer.initTransactions();

        try {
            kafkaProducer.beginTransaction();
            for (int i = 0; i < 10; i++) {

                if (i==8) {
                    int j = 10/0;
                }
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic12", "tranactions", "error data"+i);
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
}

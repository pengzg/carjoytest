package xyz.carjoy.kafka.kafka01;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.util.Properties;
import java.util.Set;

public class KafkaDMLTest {
    public static void main(String[] args) throws  Exception{
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS:9092");
        AdminClient adminClient = KafkaAdminClient.create(props);

        // 查看topic列表
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> names = listTopicsResult.names().get();
        for (String name: names
        ) {
            System.out.println(name);
        }
        adminClient.close();
        System.out.println("kafkatest");
    }
}

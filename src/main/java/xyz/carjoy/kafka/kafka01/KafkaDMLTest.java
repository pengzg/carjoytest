package xyz.carjoy.kafka.kafka01;

import org.apache.kafka.clients.admin.*;

import java.util.*;

public class KafkaDMLTest {
    public static void main(String[] args) throws  Exception{
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS:9092");
        AdminClient adminClient = KafkaAdminClient.create(props);

        // 创建topic信息
//        CreateTopicsResult topicResult = adminClient.createTopics(Arrays.asList(new NewTopic("topic02", 1, new Short("1"))));
//        topicResult.all().get();
        // 查看topic列表
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> names = listTopicsResult.names().get();
        for (String name: names
        ) {
            System.out.println(name);
        }

        // 删除topic
//        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList("topic01", "topic02"));
//        deleteTopicsResult.all().get();

        // 查看topic详细信息
//        DescribeTopicsResult dtr = adminClient.describeTopics(Arrays.asList("topic01"));
//        Map<String, TopicDescription> stringTopicDescriptionMap = dtr.all().get();
//        for (Map.Entry<String,TopicDescription> entry:stringTopicDescriptionMap.entrySet()) {
//            System.out.println(entry.getKey()+"\t"+entry.getValue());
//        }
//        adminClient.close();
        System.out.println("kafkatest");
    }
}

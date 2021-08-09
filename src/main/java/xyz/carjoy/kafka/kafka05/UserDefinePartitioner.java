package xyz.carjoy.kafka.kafka05;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义分区策略
 */
public class UserDefinePartitioner implements Partitioner {

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object val, byte[] valBytes, Cluster cluster) {
        System.out.println("partition");
        List<PartitionInfo> partitionInfoList =  cluster.partitionsForTopic(topic);
        int  numPartitions = partitionInfoList.size();

        if (keyBytes == null){
            int andIncrement = counter.getAndIncrement();
        }

        return 0;
    }

    @Override
    public void close() {
        System.out.println("close");
    }

    @Override
    public void configure(Map<String, ?> map) {
        System.out.println("configure");
    }
}

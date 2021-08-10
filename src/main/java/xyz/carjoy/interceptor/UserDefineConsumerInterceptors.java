package xyz.carjoy.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Map;

public class UserDefineConsumerInterceptors implements ConsumerInterceptor {
    @Override
    public ConsumerRecords onConsume(ConsumerRecords consumerRecords) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void onCommit(Map map) {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

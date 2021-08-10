package xyz.carjoy.kafka.kafka08;



import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

public class UserDefineSerializer implements Serializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        System.out.println("configure");
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        return SerializationUtils.serialize((Serializable) data);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return SerializationUtils.serialize((Serializable) data);
    }

    @Override
    public void close() {

        Serializer.super.close();
    }
}

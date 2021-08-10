package xyz.carjoy.kafka.kafka07;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserDefineDeserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {
        System.out.println("configure");
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return SerializationUtils.deserialize(data);
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return SerializationUtils.deserialize(data);
    }

    @Override
    public void close() {
        System.out.println("close");
        Deserializer.super.close();
    }
}

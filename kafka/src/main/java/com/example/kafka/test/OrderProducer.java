package com.example.kafka.test;


import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class OrderProducer {
    public static KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop1:9092,hadoop2:9092,hadoop3:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("buffer.memory", 33554432);
        props.put("compression.type", "lz4");
        props.put("batch.size", 32768);
        props.put("linger.ms", 100);
        props.put("retries", 10);//5 10
        props.put("retry.backoff.ms", 300);
        props.put("request.required.acks", "1");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        return producer;
    }

    public static JSONObject createRecord() {
        JSONObject order = new JSONObject();
        order.put("userId", 12344);
        order.put("amount", 100.0);
        order.put("statement", "pay");
        return order;
    }

    public static void main(String[] args) throws Exception {
        KafkaProducer<String, String> producer = createProducer();
        JSONObject order = createRecord();
        ProducerRecord<String, String> record = new ProducerRecord<>("tellYourDream", order.getString("userId"), order.toString());
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
            }
        });
    }
}
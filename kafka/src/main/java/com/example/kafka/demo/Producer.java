package com.example.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class Producer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.83.201:9092");
        props.put("acks", "0");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息
        String topic = "nokia";
        org.apache.kafka.clients.producer.Producer<String, String> procuder = new KafkaProducer<String, String>(props);
        String newPathName = "";
        String date;
        for (int i = 1; i < 1000; i++) {
            Thread.sleep(2000);
            date = new Date().toString().substring(11);
            String line = "这是第" + i + "message 时间是  " + date;
            procuder.send(new ProducerRecord<String, String>(topic, line));
            System.out.println(line);
        }
    }
}

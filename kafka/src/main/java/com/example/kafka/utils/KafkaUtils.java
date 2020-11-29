package com.example.kafka.utils;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaUtils {

    public static Properties config() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.130.7.208:9092");
        props.put("group.id", "test");
//        props.put("enable.auto.commit", "true");// 显示设置偏移量自动提交
//        props.put("auto.commit.interval.ms", "1000");// 设置偏移量提交时间间隔
//        props.put("auto.offset.reset","earlier");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }

    public static void getTopicList() throws Exception {
        AdminClient ac = AdminClient.create(config());
        Set<String> topics = ac.listTopics().names().get();

        for (String topic : topics) {
            System.out.println(topic);
        }
        ac.close();
    }

    public static void createTopic() {
        AdminClient ac = AdminClient.create(config());
        NewTopic topic = new NewTopic("kafka", 3, (short) 1);
        ac.createTopics(Collections.singletonList(topic));
        ac.close();
    }

    //遍历所有的topic
    public static void descTopic() throws ExecutionException, InterruptedException {
        AdminClient ac = AdminClient.create(config());
        List<String> list = new ArrayList();
        list.add("test");
        list.add("kafka");
        DescribeTopicsResult topicsResult = ac.describeTopics(list);
        final KafkaFuture<TopicDescription> kafka = topicsResult.values().get("kafka");
        System.out.println("topic name is "+kafka.get().name()+"---topic partition num is "+kafka.get().partitions().size());
    }

    //对已有的topic修改partition
    public static void addPartition(String topic,int num) throws ExecutionException, InterruptedException {
        AdminClient ac = AdminClient.create(config());
        Map<String, NewPartitions> map = new HashMap<>();
        NewPartitions newPartitions = NewPartitions.increaseTo(5);
        map.put("kafka",newPartitions);
        ac.createPartitions(map).all().get();
    }

    public void getaaa(){

    }




    public static void main(String[] args) throws Exception {
//        createTopic();
//        getTopicList();
        descTopic();

//        addPartition("kafka",5);
    }


}

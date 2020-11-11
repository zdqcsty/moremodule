package com.example.kafka.demo;

import com.example.kafka.utils.KafkaUtils;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class Consumer {


    public static void main(String[] args) {

    }

    public static void autoCommit() throws InterruptedException {
        Properties props = KafkaUtils.config();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("nokia"));

        while (true) {
            Thread.sleep(5000);
            ConsumerRecords<String, String> records = consumer.poll(5000);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("partition=%d ,offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
        }
    }

    public static void CommitByHand() throws InterruptedException {
        Properties props = KafkaUtils.config();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("nokia"));

        try {
            int minCommitSize = 10;// 最少处理10 条消息后才进行提交
            int icount = 0;// 消息计算器
            while (true) {
                // 等待拉取消息
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    // 简单打印出消息内容,模拟业务处理
                    System.out.printf("partition = %d, offset = %d,key= %s value = %s%n", record.partition(), record.offset(), record.key(), record.value());
                    icount++;
                }
                // 在业务逻辑处理成功后提交偏移量
                if (icount >= minCommitSize) {
                    consumer.commitAsync(new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                            if (null == exception) {
                                // TODO 表示偏移量成功提交
                                System.out.println("提交成功");
                            } else {
                                System.out.println("发生了异常");
                            }
                        }
                    });
                    icount = 0; // 重置计数器
                }
            }
        } catch (Exception e) {
            // TODO 异常处理
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

}

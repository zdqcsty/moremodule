package com.example.test;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test1 {

    static String str = "demoaaa  ${aaa}";

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "");
        StringSubstitutor sub = new StringSubstitutor(map);
        final String aaa = sub.replace(str);
        System.out.println(aaa);
    }

    public static BlockingQueue<String> getBlock() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                        queue.put("aaa"+i);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        return queue;
    }
}

package com.example.test.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Demo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            Thread.sleep(5000);
            String s = UUID.randomUUID().toString();
            LOGGER.info(s + "----第" + i++ + "个 log");
            try {
                exception();
            } catch (Exception e) {
                LOGGER.error("has a exception",e);
            }
        }
    }

    public static void exception() throws Exception {
        throw new Exception("has a exception");
    }
}

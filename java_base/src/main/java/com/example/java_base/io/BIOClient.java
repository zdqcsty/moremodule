package com.example.java_base.io;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class BIOClient {

    public static void main(String[] args){
        int count = 10;
        //计数器，模拟10个线程
        final CountDownLatch latch = new CountDownLatch(count);
        for(int i = 0 ; i < count; i ++){
            new Thread(){
                @Override
                public void run() {
                    try{
                        //等待，保证线程一个一个创建连接
                        latch.await();
                        //创建socket,连接服务端
                        Socket client = new Socket("localhost", 9999);
                        //获取输出流
                        OutputStream os = client.getOutputStream();
                        //获取当前线程名
                        String name = "客户端线程："+Thread.currentThread().getName();
                        //发送到服务端
                        os.write(name.getBytes());
                        //关闭输入流
                        os.close();
                        //关闭socket连接
                        client.close();

                    }catch(Exception e){

                    }
                }

            }.start();
            //计数器减1
            latch.countDown();
        }
    }
}
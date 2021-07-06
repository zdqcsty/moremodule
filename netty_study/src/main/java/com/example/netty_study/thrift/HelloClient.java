package com.example.netty_study.thrift;

import com.example.netty_study.server.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class HelloClient {
    public static void main(String[] args) {
        TTransport transport = null;
        try {
            //传输层使用阻塞I/O
            transport = new TSocket("127.0.0.1", 9090);
            transport.open();
            //使用二进制协议传输数据
            TProtocol protocol = new TBinaryProtocol(transport);
            //使用同步客户端
            HelloService.Client client = new HelloService.Client(protocol);
            String name = "XuDT";
            //调用接口
            String result = client.sayHello(name);
            System.out.println(result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }
}
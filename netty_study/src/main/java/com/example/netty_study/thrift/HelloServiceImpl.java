package com.example.netty_study.thrift;

import com.example.netty_study.server.HelloService;
import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface {


    @Override
    public String sayHello(String name) throws TException {
        return "aaa";
    }
}

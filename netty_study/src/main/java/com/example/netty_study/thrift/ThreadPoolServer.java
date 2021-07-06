package com.example.netty_study.thrift;

import com.example.netty_study.server.HelloService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ThreadPoolServer {

    public static void main(String[] args) {
        try {
            //监听端口9090
            TServerSocket serverTransport = new TServerSocket(9090);
            //使用二进制协议传输数据
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
            //关联处理器与HelloService服务实现
            TProcessor processor = new HelloService.Processor(new HelloServiceImpl());
            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
            TProcessorFactory tProcessorFactory = new TProcessorFactory(processor);

            serverArgs.processorFactory(tProcessorFactory);
//            serverArgs.processor(processor);
            serverArgs.protocolFactory(proFactory);
            //使用TThreadPoolServer服务端
            TServer server = new TThreadPoolServer(serverArgs);
            //启动服务
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}

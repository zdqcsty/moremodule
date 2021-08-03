package com.example.java_base.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @ProjectName: onereader
 * @Package: com.onereader.webblog.common.bio
 * @ClassName: BIOServer
 * @Author: onereader
 * @Description: ${description}
 * @Date: 2019/9/1 14:30
 * @Version: 1.0
 */
public class BIOServer {

    ServerSocket server;
    //服务器
    public BIOServer(int port){
        try {
            //把Socket服务端启动
            server = new ServerSocket(port);
            System.out.println("BIO服务已启动，监听端口是：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始监听，并处理逻辑
     * @throws IOException
     */
    public void listener() throws IOException{
        //死循环监听
        while(true){
            //等待客户端连接，阻塞方法
            Socket client = server.accept();
            //获取输入流
            InputStream is = client.getInputStream();
            //定义数组，接收字节流
            byte [] buff = new byte[1024];
            int len = is.read(buff);
            //只要一直有数据写入，len就会一直大于0
            if(len > 0){
                String msg = new String(buff,0,len);
                System.out.println("收到" + msg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(9999).listener();
    }
}


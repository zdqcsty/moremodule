package com.example.java_base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @ProjectName: onereader
 * @Package: com.onereader.webblog.common.nio
 * @ClassName: NIOServer
 * @Author: onereader
 * @Description: ${description}
 * @Date: 2019/9/1 13:23
 * @Version: 1.0
 */
public class NIOServer {

    private int port = 9999;
    private Charset charset = Charset.forName("UTF-8");
    private Selector selector = null;

    public NIOServer(int port) throws IOException{

        this.port = port;
        //1.打开通道
        ServerSocketChannel server = ServerSocketChannel.open();
        //设置服务端口
        server.bind(new InetSocketAddress(this.port));
        server.configureBlocking(false);
        //2.打开选择器
        selector = Selector.open();
        //注册等待事件
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务已启动，监听端口是：" + this.port);
    }

    /**
     *  监听事件
     * @throws IOException
     */
    public void listener() throws IOException{
        //死循环，这里不会阻塞
        while(true) {
            //1.在轮询获取待处理的事件
            int wait = selector.select();
            System.out.println("当前等待处理的事件："+wait+"个");
            if(wait == 0){
                //如果没有可处理的事件，则跳过
                continue;
            }
            //获取所有待处理的事件
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            //遍历
            while(iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                //处理前，关闭选在择器中的事件
                iterator.remove();
                //处理事件
                process(key);
                System.out.println("事件Readable："+key.isReadable());
                System.out.println("事件Acceptable："+key.isAcceptable());
            }
        }

    }

    /**
     * 根据事件类型，做处理
     * @param key
     * @throws IOException
     */
    public void process(SelectionKey key) throws IOException {
        //连接就绪
        if(key.isAcceptable()){
            //获取通道
            ServerSocketChannel server = (ServerSocketChannel)key.channel();
            //进入服务端等待
            SocketChannel client = server.accept();
            //非阻塞模式
            client.configureBlocking(false);
            //注册选择器，并设置为读取模式，收到一个连接请求，
            // 然后起一个SocketChannel，并注册到selector上，
            // 之后这个连接的数据，就由这个SocketChannel处理
            client.register(selector, SelectionKey.OP_READ);
            //将此对应的channel设置为准备接受其他客户端请求
            key.interestOps(SelectionKey.OP_ACCEPT);
            client.write(charset.encode("来自服务端的慰问"));
        }
        //读就绪
        if(key.isReadable()){
            //返回该SelectionKey对应的 Channel，其中有数据需要读取
            SocketChannel client = (SocketChannel)key.channel();

            //往缓冲区读数据
            ByteBuffer buff = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            try{
                while(client.read(buff) > 0){
                    buff.flip();
                    content.append(charset.decode(buff));
                }
                System.out.println("接收到客户端："+content.toString());
                //将此对应的channel设置为准备下一次接受数据
                key.interestOps(SelectionKey.OP_READ);
            }catch (IOException io){
                key.cancel();
                if(key.channel() != null){
                    key.channel().close();
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new NIOServer(9999).listener();
    }


}
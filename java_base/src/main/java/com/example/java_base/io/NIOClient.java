package com.example.java_base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
/**
 * @ProjectName: onereader
 * @Package: com.onereader.webblog.common.nio
 * @ClassName: NIOClient
 * @Author: onereader
 * @Description: ${description}
 * @Date: 2019/9/1 13:41
 * @Version: 1.0
 */
public class NIOClient {
    private final InetSocketAddress serverAdrress = new InetSocketAddress("localhost", 9999);
    private Selector selector = null;
    private SocketChannel client = null;
    private Charset charset = Charset.forName("UTF-8");

    public NIOClient() throws IOException{
        //1.连接远程主机的IP和端口
        client = SocketChannel.open(serverAdrress);
        client.configureBlocking(false);

        //打开选择器，注册读事件
        selector = Selector.open();
        client.register(selector, SelectionKey.OP_READ);
    }

    public void session(){
        //开辟一个新线程从服务器端读数据
        new Reader().start();
        //开辟一个新线程往服务器端写数据
        new Writer().start();
    }

    /**
     * 写数据线程
     */
    private class Writer extends Thread{

        @Override
        public void run() {
            try{
                //在主线程中 从键盘读取数据输入到服务器端
                Scanner scan = new Scanner(System.in);
                while(scan.hasNextLine()){
                    String line = scan.nextLine();
                    if("".equals(line)){
                        //不允许发空消息
                        continue;
                    }
                    //当前渠道是共用的，发送当前输入数据
                    client.write(charset.encode(line));
                }
                scan.close();
            }catch(Exception e){

            }
        }

    }

    /**
     * 读数据线程
     */
    private class Reader extends Thread {
        @Override
        public void run() {
            try {
                //循环检测
                while(true) {
                    int readyChannels = selector.select();
                    if(readyChannels == 0){
                        continue;
                    }
                    //获取selected所有的事件
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                    while(keyIterator.hasNext()) {
                        SelectionKey key = (SelectionKey) keyIterator.next();
                        keyIterator.remove();
                        process(key);
                    }
                }
            }
            catch (IOException io){

            }
        }

        /**
         * 根据事件的不同，做不同的处理
         * @param key
         * @throws IOException
         */
        private void process(SelectionKey key) throws IOException {
            //读就绪事件
            if(key.isReadable()){
                //通过key找到对应的通道
                SocketChannel sc = (SocketChannel)key.channel();
                //创建缓存区
                ByteBuffer buff = ByteBuffer.allocate(1024);
                String content = "";
                //读数据
                while(sc.read(buff) > 0){
                    buff.flip();
                    content += charset.decode(buff);
                }
                //打印内容
                System.out.println(content);
                //设置当前为读就绪
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NIOClient().session();
    }
}


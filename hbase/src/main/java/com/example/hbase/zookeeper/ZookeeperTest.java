package com.example.hbase.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.nodes.PersistentEphemeralNode;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZookeeperTest {

    public static void main(String[] args) throws Exception {

        //创建zookeeper 客户端
        //一个应用中需要一个ZK实例就足够了.CuratorFramework实例都是线程安全的
        final CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("hebing1.novalocal:2188,hebing2.novalocal:2188,hebing3.novalocal:2188")
                .retryPolicy(new ExponentialBackoffRetry(2000, 19)).build();

        client.start();
//        //创建持久节点  创建父目录（相当于）
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/demoaabc");

        //创建znode节点  并写上数据
        //临时节点会随着session的断开而删除
        PersistentEphemeralNode znode =
                new PersistentEphemeralNode(client,
                        PersistentEphemeralNode.Mode.EPHEMERAL_SEQUENTIAL, "/demoaabc/ccccd", "hahahah".getBytes());



        znode.start();

        Thread.sleep(10000);

        System.out.println("-------------");
        //获取全路径需要等待znode 创建好    这个需要一点时间
        System.out.println(znode.getActualPath());
        System.out.println("-------------");

    }

}



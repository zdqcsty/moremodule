package com.example.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class Test2 {

    public static void main(String[] args) throws InterruptedException {

        Configuration conf = new Configuration();
        conf.addResource(new Path("test/src/main/resources/hdfs-site.xml"));
        final String s = conf.get("dfs.namenode.rpc-address");
        System.out.println(s);




    }
}

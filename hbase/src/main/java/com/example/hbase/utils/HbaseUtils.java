package com.example.hbase.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 参考   https://hbase.apache.org/1.4/book.html#hbase_apis
 */

public class HbaseUtils {

    public static void main(String[] args) throws IOException {

//        createTable("demoaaa","bbb");
        putData("demoaaa", "1", "bbb", "aaa", "ccc");
    }


    public static Configuration getConf() throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("hbase-site.xml"));
        config.addResource(new Path("core-site.xml"));
        return config;
    }

    public static void createTable(String tableName, String columnFamily) throws IOException {
        Configuration config = getConf();
        try (Connection conn = ConnectionFactory.createConnection(config);
             Admin admin = conn.getAdmin()) {
            HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
            table.addFamily(new HColumnDescriptor(columnFamily).setCompressionType(Compression.Algorithm.SNAPPY));
            admin.createTable(table);
        }
    }

    /**
     * 为hbase添加一条数据
     */
    public static void putData(String tableName, String row, String columnFamily,
                               String column, String data) throws IOException {
        Configuration config = getConf();
        try (Connection conn = ConnectionFactory.createConnection(config)) {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(row));
            put.addColumn(columnFamily.getBytes(), column.getBytes(), data.getBytes());
            table.put(put);
        }
    }


}

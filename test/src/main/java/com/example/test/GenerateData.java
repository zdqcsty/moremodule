package com.example.test;


import cn.binarywang.tools.generator.*;
import com.example.test.listen.Demo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class GenerateData {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateData.class);


    public static void main(String[] args) throws IOException, InterruptedException {

        //身份证号码
        ChineseIDCardNumberGenerator cidcng = (ChineseIDCardNumberGenerator) ChineseIDCardNumberGenerator.getInstance();
        //中文姓名
        ChineseNameGenerator cng = ChineseNameGenerator.getInstance();
        //英文姓名
        EnglishNameGenerator eng = EnglishNameGenerator.getInstance();
        //手机号
        ChineseMobileNumberGenerator cmng = ChineseMobileNumberGenerator.getInstance();
        //电子邮箱
        EmailAddressGenerator eag = (EmailAddressGenerator) EmailAddressGenerator.getInstance();
        //居住地址
        ChineseAddressGenerator cag = (ChineseAddressGenerator) ChineseAddressGenerator.getInstance();

        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat timeStampFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        FileSystem fs = getFileSystem();

        //如果包含中文，推荐这种方式
        FSDataOutputStream dos = fs.create(new Path("/user/zgh/tmp/dataaaaa.csv"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(dos, "utf-8"));

        int flag = 0;

        int total = 0;

        for (int i = 0; i < 100000000; i++) {
            StringJoiner sj = new StringJoiner(",");
            String cidcng_data = cidcng.generate();
            String cng_data = cng.generate();
            String eng_data = eng.generate();
            String cmng_data = cmng.generate();
            String eag_data = eag.generate();
            String cag_data = cag.generate();

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);
            sj.add(getFloat());
            sj.add(getDouble());
            sj.add(getInt());
            sj.add(getDecimal());
            sj.add(getBool());
            sj.add(dateFmt.format(new Date().getTime()));
            sj.add(timeStampFmt.format(new Date().getTime()));

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);

            sj.add(cidcng_data);
            sj.add(cng_data);
            sj.add(eng_data);
            sj.add(cmng_data);
            sj.add(eag_data);
            sj.add(cag_data);


            bw.write(sj.toString() + "\n");

            flag++;

            if (flag == 10000) {

                total = total + 10000;
                flag = 0;
//                LOGGER.info("Number of completed rows " + total);
            }

            Thread.sleep(1000);
        }
        bw.close();
        fs.close();
    }

    public static String getFloat() {
        double a = Math.random() * 10;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(a);
    }

    public static String getDouble() {
        double a = Math.random() * 20;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(a);
    }

    public static String getInt() {
        return Math.random() * 100 + "";
    }

    public static String getBool() {
        return "true";
    }

    public static String getDecimal() {
        double a = Math.random() * 30;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(a);
    }

    public static FileSystem getFileSystem() {

        //以我们公司自己的合并环境当作示例
        System.setProperty("HADOOP_USER_NAME", "hadoop");   //以Hadoop用户，不然报权限错误
        FileSystem fileSystem = null;
        Configuration conf = new Configuration();
        conf.addResource("core-site.xml");
        conf.addResource("hdfs-site.xml");

        try {
            fileSystem = FileSystem.get(conf);
        } catch (IOException e) {
        }
        return fileSystem;
    }

}

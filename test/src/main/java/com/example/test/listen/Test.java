package com.example.test.listen;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws Exception {
        // 监控目录
//        String rootDir = "/home/hadoop/zgh/tmp/ceshi";
        String rootDir = "E:\\test";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        // 使用过滤器
//        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
        //不使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
        File file = new File(rootDir, UUID.randomUUID().toString() + ".txt");
        System.out.println("----------"+file.getAbsolutePath());
        PrintWriter pw = new PrintWriter(file.getAbsolutePath());
//        PrintWriter pw = new PrintWriter("E:\\test\\ccccacda.txt");
        pw.print("aaa");
        pw.close();
        Thread.sleep(1000);
        monitor.stop(0);
    }
}

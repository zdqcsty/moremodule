package com.example.java_base.io;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class aaa {

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException, IOException {
        int totals = 100000000;
        int segment = 20;
        ExecutorService service = Executors.newFixedThreadPool(segment);
        // 将文件拆分
        long s = System.currentTimeMillis();
        splitFileByLine("E:\\demo.csv", "E:\\", totals / segment);
        System.out.println("拆分文件耗時： " + (System.currentTimeMillis() - s));
        AtomicInteger incr = new AtomicInteger(1);

        AtomicInteger total = new AtomicInteger(0);
        CountDownLatch downLatch = new CountDownLatch(segment);
        for (int i = 1; i <= segment; i++) {
            service.execute(() -> {
                try {
                    readFile("E:\\last-" + incr.getAndIncrement() + ".txt", line -> {
                        //这里千万不要 打印 line ， 太耗时了
                        total.getAndIncrement();
                    });
                } finally {
                    downLatch.countDown();
                }
            });
        }
        downLatch.await();
        System.out.println("读取文件总耗时：" + (System.currentTimeMillis() - s) + " , 读取数据行：" + total.get());
        service.shutdown();
    }

    /**
     * 按行分割文件
     *
     * @param sourceFilePath      为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     * @param rows                为多少行一个文件
     */
    public static int splitFileByLine(String sourceFilePath, String targetDirectoryPath, int rows) {
        String sourceFileName = sourceFilePath.substring(sourceFilePath.lastIndexOf(File.separator) + 1,
                sourceFilePath.lastIndexOf("."));// 源文件名
        String splitFileName = targetDirectoryPath + File.separator + sourceFileName + "-%s.txt";// 切割后的文件名
        File targetDirectory = new File(targetDirectoryPath);
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }
        PrintWriter pw = null;// 字符输出流
        String tempLine;
        int lineNum = 0;// 本次行数累计 , 达到rows开辟新文件
        int splitFileIndex = 1;// 当前文件索引

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFilePath)))) {
            pw = new PrintWriter(String.format(splitFileName, splitFileIndex));
            while ((tempLine = br.readLine()) != null) {
                if (lineNum > 0 && lineNum % rows == 0) {// 需要换新文件
                    pw.flush();
                    pw.close();
                    pw = new PrintWriter(String.format(splitFileName, ++splitFileIndex));
                }
                pw.write(tempLine + "\n");
                lineNum++;
            }
            return splitFileIndex;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (null != pw) {
                pw.flush();
                pw.close();
            }
        }
    }

    public interface Callback {
        public void onReceive(String line);
    }

    /**
     * 大文件读取
     *
     * @param filePath
     */
    public static void readFile(String filePath, Callback callback) {
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file), 20 * 1024 * 1024); // 如果是读大文件，设置缓存
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                callback.onReceive(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

package com.example.test.listen;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

public class FileListener extends FileAlterationListenerAdaptor {

    @Override
    public void onFileCreate(File file) {

        System.out.println("------------"+file.getName());

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------"+file.getName());
    }
}

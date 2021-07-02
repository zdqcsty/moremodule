package com.example.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter pw=new PrintWriter("E:\\test\\ccc.txt");
        pw.println("aa");
        pw.close();
    }
}

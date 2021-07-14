package com.example.test.xcloud;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class XcloudTest {

    public static final String DRIVER = "com.bonc.xcloud.jdbc.XCloudDriver";
    public static String JDBCURL = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static String SQL = "";

    static {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(new File("config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JDBCURL = prop.getProperty("jdbcurl").trim();
        USERNAME = prop.getProperty("username").trim();
        PASSWORD = prop.getProperty("password").trim();
        SQL = prop.getProperty("sql").trim();
    }

    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
        conn.prepareStatement(SQL).execute();
        System.out.println("------------success-----------------");
    }
}

package com.example.test.gbasejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneralGbase {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        192.168.1.34,192.168.1.35,192.168.1.37
        Class.forName("com.gbase.jdbc.Driver");
        String url = "jdbc:gbase://10.130.7.228:5258/protest";
        String user = "root";
        String passwd = "gtest321";
        Connection connect = DriverManager.getConnection(url, user, passwd);
        Statement statement = null;
        statement = connect.createStatement();
        statement.execute("set gbase_hdfs_protocol=RPC");
        statement.execute("set gbase_hdfs_auth_mode=simple");
        statement.execute("set gbase_hdfs_namenodes='hebing1.novalocal,hebing2.novalocal'");
//        statement.execute("set gbase_hdfs_namenodes='192.168.1.73,192.168.1.110'");
        statement.execute("SELECT * FROM protest.aaa INTO OUTFILE 'hdp://hadoop@hebing2.novalocal:8020/user/zgh/null/csv' CHARACTER SET UTF8  OUTFILEMODE BY HDFS FIELDS TERMINATED BY 'a' DOUBLE_ENCLOSED BY '\"'");
        statement.close();
        connect.close();
    }
}
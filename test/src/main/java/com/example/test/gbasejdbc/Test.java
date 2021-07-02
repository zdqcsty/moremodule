package com.example.test.gbasejdbc;

import java.sql.*;

public class Test {

    public static final String url = "jdbc:gbase://10.130.7.228:5258/protest";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.gbase.jdbc.Driver");

        String sql = "LOAD DATA INFILE 'hdp://hadoop@192.168.1.73:8020/user/zgh/tmp/democcc/demob.csv' INTO TABLE z_test CHARACTER SET UTF8;";
        String user = "root";
        String passwd = "gtest321";
        Connection connect = DriverManager.getConnection(url, user, passwd);
        Statement statement = null;
        statement = connect.createStatement();

        statement.execute("set gbase_hdfs_auth_mode=simple");
        statement.execute("set gbase_hdfs_protocol=RPC");
//        statement.execute("set gbase_hdfs_principal=hadoop");

        statement.execute("set gbase_hdfs_namenodes='192.168.1.73,192.168.1.110'");
//        statement.execute("SELECT * FROM aaa INTO OUTFILE 'hdp://hadoop@192.168.1.73:8020/user/zgh/tmp/democcc' OUTFILEMODE BY HDFS FIELDS TERMINATED BY 'a' DOUBLE_ENCLOSED BY '\"' ");
        statement.execute(sql);
        statement.close();
        connect.close();
    }
}

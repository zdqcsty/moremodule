package com.example.test.gbasejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class KerberosGbase {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:gbase://10.130.7.228:5258/protest";
        String user = "root";
        String passwd = "gtest321";
        Connection connect = DriverManager.getConnection(url, user, passwd);
        Statement statement = null;
        statement = connect.createStatement();
        statement.execute("set gbase_hdfs_protocol=RPC");
        statement.execute("set gbase_hdfs_auth_mode=kerberos");
//        statement.execute("set gbase_hdfs_protocol=http");
        statement.execute("set gbase_hdfs_principal='science@BONC.COM'");
        statement.execute("set gbase_hdfs_keytab=");
        statement.execute("set gbase_hdfs_namenodes='beh61.bonc.com,beh62.bonc.com'");
        statement.execute("SELECT * FROM protest.aaa INTO OUTFILE 'hdp://hadoop@hebing1.novalocal:8020/user/zgh/tmp/demoaaa' OUTFILEMODE BY HDFS");
        statement.close();
        connect.close();
    }
}

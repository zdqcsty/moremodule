/*
package com.example.test.jdbc;

public class Demo {

    public static void main(String[] args) {

        String url = "jdbc:gbase://10.130.7.228:5258/protest";
        String user = "root";
        String passwd = "gtest321";
        try {
            Connection connect = DriverManager.getConnection(url, user, passwd);
            Statement statement=null;
            statement = connect.createStatement();
            statement.execute("set gbase_hdfs_protocol=RPC");
            statement.execute("set gbase_hdfs_auth_mode=kerberos");
            statement.execute("set gbase_hdfs_protocol=http");
            statement.execute("set gbase_hdfs_principal='science@BONC.COM'");
            statement.execute("set gbase_hdfs_namenodes='beh61.bonc.com,beh62.bonc.com'");
            statement.execute("SELECT * FROM protest.aaa INTO OUTFILE 'hdp://science@beh61.bonc.com:19000/yidong/datascience/import-file/dsddd/' OUTFILEMODE BY HDFS");
            statement.close();
            connect.close();
        }catch (Exception w){
            w.printStackTrace();
            System.out.printf("异常："+w.getMessage());
        }
    }
}
*/

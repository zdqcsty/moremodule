package com.example.test.xcloud;

import java.sql.*;

public class XcloudJdbc {

    public static final String DRIVER = "com.bonc.xcloud.jdbc.XCloudDriver";
    public static String JDBCURL = "jdbc:xcloud:@172.16.26.37:1804/CIRRO_DB";
    public static String USERNAME = "CIRRO_USER";
    public static String PASSWORD = "bonc123";


    static String sql3 = "EXPORT /*+ WITH_FIELD_CAPTION*/  select t0.DATE_ID as DATE_ID  ,t0.CUST_ID as CUST_ID  ,t0.GROUP_ID as GROUP_ID  ,t0.GROUP_NAME as GROUP_NAME  ,t0.GROUP_TYPE as GROUP_TYPE  ,t0.GROUP_STATUS as GROUP_STATUS  ,t0.GROUP_ADDR as GROUP_ADDR  ,t0.AREA_ID as AREA_ID  ,t0.CITY_ID as CITY_ID  ,t0.SUPER_GROUP_ID as SUPER_GROUP_ID  ,t0.CUST_MANAGER_ID as CUST_MANAGER_ID  ,t0.CALLING_TYPE_CODE as CALLING_TYPE_CODE  ,t0.SUB_CALLING_TYPE_CODE as SUB_CALLING_TYPE_CODE  ,t0.GROUP_CONTACT_PHONE as GROUP_CONTACT_PHONE  ,t0.ENTERPRISE_TYPE_CODE as ENTERPRISE_TYPE_CODE  ,t0.ENTERPRISE_SIZE_CODE as ENTERPRISE_SIZE_CODE  ,t0.JURISTIC_TYPE_CODE as JURISTIC_TYPE_CODE  ,t0.JURISTIC_CUST_ID as JURISTIC_CUST_ID  ,t0.JURISTIC_NAME as JURISTIC_NAME  ,t0.BUSI_LICENCE_TYPE as BUSI_LICENCE_TYPE  ,t0.BUSI_LICENCE_NO as BUSI_LICENCE_NO  ,t0.BUSI_LICENCE_VALID_DATE as BUSI_LICENCE_VALID_DATE  ,t0.GROUP_PAY_MODE as GROUP_PAY_MODE  ,t0.REMOVE_TAG as REMOVE_TAG  ,t0.REMOVE_DATE as REMOVE_DATE  ,t0.REMOVE_STAFF_ID as REMOVE_STAFF_ID  ,t0.REMOVE_CHANGE as REMOVE_CHANGE  ,t0.UPDATE_TIME as UPDATE_TIME  ,t0.UPDATE_STAFF_ID as UPDATE_STAFF_ID  ,t0.UPDATE_DEPART_ID as UPDATE_DEPART_ID  ,t0.CLASS_ID as CLASS_ID  ,t0.ACCEPT_CHANNEL as ACCEPT_CHANNEL  ,t0.AUDIT_STATE as AUDIT_STATE  ,t0.AUDIT_DATE as AUDIT_DATE  ,t0.AUDIT_STAFF_ID as AUDIT_STAFF_ID  ,t0.CALLING_TYPE_CODE_DETAIL as CALLING_TYPE_CODE_DETAIL  ,t0.TAKE_PICTURE_TAG as TAKE_PICTURE_TAG  ,t0.CUST_MANAGER_ID_B as CUST_MANAGER_ID_B  ,t0.BELONG_TEAM_ID as BELONG_TEAM_ID  ,t0.BELONG_TEAM_ID_B as BELONG_TEAM_ID_B  ,t0.JURISTIC_PSPT_ID as JURISTIC_PSPT_ID  ,t0.JURISTIC_PSPT_TYPE as JURISTIC_PSPT_TYPE  ,t0.DEVELOP_DEPART_ID as DEVELOP_DEPART_ID  ,t0.INNET_USERS as INNET_USERS  ,t0.PBB_INNET_USERS as PBB_INNET_USERS  ,t0.OUTNET_USERS as OUTNET_USERS  ,t0.PBB_OUTNET_USERS as PBB_OUTNET_USERS  ,t0.INNET_MONTHS as INNET_MONTHS  from  DWA.DWA_D_CUS_GROUP t0 ATTRIBUTE(LOCATION('HDFS:/tmp/fa365687-1e93-42f9-bf92-c31d3f26d642aaa') DIRECTORY PREFIX ('xclouddata') SEPARATOR(',') DELIMITER('\"'))";


    public static void main(String[] args) throws Exception {
        String sql8 = "EXPORT /*+ WITH_FIELD_CAPTION*/  select * from DWA_V_M_CUS_AL_USER_INFO_YD t0 ATTRIBUTE(LOCATION('HDFS:/tmp_doc/cirro_data_DWA_V_M_CUS_AL_USER_INFO_YD') DIRECTORY PREFIX ('xclouddata') SEPARATOR(',') DELIMITER('\"'))";

        String sql = "select * from USERINFO0115LXM limit 10";
        String sql6 = "select * from democcc limit 10";
        String sql4 = "create table democcc (id int , name varchar(10))";

        String sql5 = "insert into democcc(id,name)  'HDFS:/tmp/demo.csv' SEPARATOR ',' UNQUOTED";
//        String sql1 = "EXPORT /*+ WITH_FIELD_CAPTION*/ SELECT * FROM USERINFO0115LXM ATTRIBUTE(LOCATION('HDFS:/tmp/democcc'))";
        String sql2 = "EXPORT /*+ WITH_FIELD_CAPTION*/ SELECT * FROM USERINFO0115LXM ATTRIBUTE(LOCATION('HDFS:/tmp/demoddd') DIRECTORY PREFIX ('xclouddata')) ";


        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
        conn.prepareStatement(sql8).execute();
/*        ResultSet resultSet = conn.prepareStatement(sql6).executeQuery();
        while (resultSet.next()) {
            String string = resultSet.getString(1);
            System.out.println(string);
        }*/
    }

}

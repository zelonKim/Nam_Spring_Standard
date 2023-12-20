package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest2 {
    public static void main(String[] args) throws Exception {

        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터소스 객체.getConnection(): 데이터베이스 연결 객체(Connection)를 얻어옴.

        //////////////////////////////////////

        Statement stmt  = conn.createStatement();

        String query = "select * from user_info";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String data = rs.getString(1);
            System.out.println(data);  // asdf  qwer  zxcv
        }
    }
}

package com.fastcampus.ch3;

import java.sql.*;

public class DBConnectionTest {
    public static void main(String[] args) throws Exception {
        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";  //  jdbc:mysql://ip주소:포트번호/스키마명
        String DB_USER = "root";
        String DB_PASSWORD = "ksz40204";

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // DriverManager.getConncetion("URL", "유저명", "비밀번호"): 데이터베이스 연결 객체(Connection)를 얻어옴.

        //////////////////////////////////////

        Statement stmt  = conn.createStatement(); // 연결 객체.createStatement(): 스테이트먼트 객체(Statement)를 생성함.

        String query = "select * from user_info";
        ResultSet rs = stmt.executeQuery(query); // 스테이트먼트 객체.executeQuery("쿼리문"): 쿼리문을 실행함.

        while (rs.next()) {
            String data = rs.getString(1);  // ResultSet.getString(인덱스): 인덱스번째 컬럼에 해당하는 값을 String으로 읽어옴.
            System.out.println(data);  // asdf  qwer  zxcv
        }
    }
}



















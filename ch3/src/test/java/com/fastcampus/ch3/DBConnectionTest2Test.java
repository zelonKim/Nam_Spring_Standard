package com.fastcampus.ch3;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

//
// // 수동
//public class DBConnectionTest2Test  {
//    @Test
//    public void main() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);
//
//        Connection conn = ds.getConnection();
//        assertTrue(conn != null);
//    }
//}
//
//
/////////////////////////
//
//
// // 자동
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // XML 설정 파일 위치를 지정해줌.
//public class DBConnectionTest2Test  {
//    @Autowired
//    DataSource ds;
//
//    @Test
//    public void main() throws Exception {
//        Connection conn = ds.getConnection();
//        assertTrue(conn != null);
//    }
//}






//////////////////////////////////////////////////






@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // XML 설정 파일 위치를 지정해줌.
public class DBConnectionTest2Test  {
    @Autowired
    DataSource ds;


    @Test
    public void insertUserTest() throws Exception {
        User user = new User("hjkl", "1234", "abc", "aaa@naver.com", new Date(),"fb", new Date());
        deleteAll();

        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);
    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        User user = new User("asdf2", "1234", "abc", "aaa@naver.com", new Date(),"fb", new Date());
        int rowCnt = insertUser(user);
        User user2 = selectUser("asdf2");

        assertTrue(user.getId().equals("asdf2"));
    }



    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        // .getString(인덱스): 주어진 인덱스에 해당하는 값을 읽어옴.
        if(rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(rs.getDate(5));
            user.setSns(rs.getString(6));

            return user;
        }
        return null;
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("asdf2");
        assertTrue(rowCnt==0);

        User user = new User("asdf2", "1234", "abc", "aaa@naver.com", new Date(),"fb", new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt==1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt==1);
    }


    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        int rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }



    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }




    public void transactionTest() throws Exception {
        Connection conn = null;
        try {
            deleteAll();
            conn = ds.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 해제함.

            String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now());";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "asdf");
            pstmt.setString(2, "1234");
            pstmt.setString(3, "abc");
            pstmt.setString(4, "aaa@naver.com");
            pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, "fb");

            int rowCnt = pstmt.executeUpdate();

            pstmt.setString(1, "asdf");
            rowCnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {

        }
    }




    public int insertUser(User user) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now());";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        // .setString(인덱스, 값):  주어진 인덱스와 일치하는 ?에 값을 채워줌.
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        int rowCnt = pstmt.executeUpdate(); // SQL문을 실행한 다음, 영향을 받은 행의 개수를 반환함.

        // .executeUpdate(): INSERT, UPDATE, DELETE 에서 사용됨.
        // .executeQuery(): SELECT 에서 사용됨.
        return rowCnt;
    }

    @Test
    public void main() throws Exception {
        Connection conn = ds.getConnection();
        assertTrue(conn != null);
    }
}







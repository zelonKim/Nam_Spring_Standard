package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

import static org.junit.Assert.*;

/*
// root-context.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:tx="http://www.springframework.org/schema/tx"
        xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/springbasic?useUnicode=true&amp;characterEncoding=utf8"></property>
        <property name="username" value="root"></property>
        <property name="password" value="ksz40204"></property>
    </bean>

    <context:component-scan base-package="com.fastcampus.ch3"/>
</beans>
*/

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class A1DaoTest {
//    @Autowired
//    A1Dao a1Dao;
//
//    @Autowired
//    DataSource ds;
//
//    @Test
//    public void insertTest() throws Exception {
//        // 트랜잭션 매니저 생성
//        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
//        // 트랜잭션 시작
//        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
//
//        try {
//            a1Dao.deleteAll();
//            a1Dao.insert(1, 100); // 성공
//            a1Dao.insert(1, 200); // 실패
//            tm.commit(status);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            tm.rollback(status);
//            // select를 해도 아무것도 조회되지 않음.
//        } finally {
//        }
//    }
//}



///////////////////////////////



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {
    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    // 트랜잭션 매니저 생성
    @Autowired
    DataSourceTransactionManager tm;

    @Test
    public void insertTest() throws Exception {
        // 트랜잭션 시작
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());

        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100); // 성공
            a1Dao.insert(1, 200); // 실패

            // a1Dao.insert(1, 100); -> com.mysql.cj.jdbc.ConnectionImpl@69eb86b4 사용
            // a1Dao.insert(1, 200); -> com.mysql.cj.jdbc.ConnectionImpl@69eb86b4 사용
            tm.commit(status);

        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
            // select를 해도 아무것도 조회되지 않음.
        } finally {
        }
    }
}


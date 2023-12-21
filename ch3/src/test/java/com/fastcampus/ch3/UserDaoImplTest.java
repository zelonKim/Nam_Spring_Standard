package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void DeleteUser() {
    }

    @Test
    public void SelectUser() {
    }

    @Test
    public void InsertUser() {
    }

    @Test
    public void UpdateUser() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2021, 1, 1);

        userDao.deleteAll();
        User user = new User("asdf","1234", "abc", "aaa@naver.com", new Date(cal.getTimeInMillis()), "fb", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt==1);

        user.setPwd("4321");
        user.setEmail("bbb@naver.com");
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt==1);

        User user2 = userDao.selectUser(user.getId());
        System.out.println(user);
        System.out.println(user2);
        assertTrue(user.equals(user2));

    }
}
package com.fastcampus.ch3.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context_aop.xml");
        MyMath mm = (MyMath)ac.getBean("myMath");

        mm.add(3, 5);
        mm.add(3, 5, 2);
        mm.multiply(3, 5); 

    }
}

/*
[start]add[3, 5]
8
[end]135ms

[start]add[3, 5, 2]
10
[end]0ms
*/

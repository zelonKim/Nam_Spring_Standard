package com.example.demo.chapter03.aop;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Aspect
@Component
public class SampleAspect {

    @Before("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("=== before ===");
        System.out.println(jp.getSignature().getName());  //  어드바이스가 적용되는 대상 메서드명을 출력함.
    }

    @After("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
    public void afterAdvice(JoinPoint jp) {
        System.out.println("=== after ===");
        System.out.println(jp.getSignature().getName());  //  어드바이스가 적용되는 대상 메서드명을 출력함.
    }



    @Around("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
    public void aroundAdvice(ProceedingJoinPoint pjp) {
        System.out.println("=== before ===");
        System.out.println(pjp.getSignature().getName()); //  어드바이스가 적용되는 대상 메서드명을 출력함.

        Object result = pjp.proceed(); // 어드바이스가 적용되는 대상 메서드를 호출함.
        return result;

        System.out.println("=== after ===");

    }

}

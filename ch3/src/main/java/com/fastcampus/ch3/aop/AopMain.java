package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for(Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }
    }
}


class MyAdvice {
    void invoke(Method m, Object obj, Object... args) throws Exception {
        System.out.println("[before]");
        m.invoke(obj, args);
        System.out.println("[after]");
    }
}


class MyClass {
    void aaa() {
        System.out.println("aaa() is called.");
    }
    void aaa2() {
        System.out.println("aaa2() is called.");
    }
    void bbb() {
        System.out.println("bbb() is called.");
    }
}
*/


/*
[before]
aaa() is called.
[after]

[before]
aaa2() is called.
[after]

[before]
bbb() is called.
[after]
*/


//////////////////////////////////////





/*
public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for(Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }
    }
}


class MyAdvice {
    Pattern p = Pattern.compile("a.*");

    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }

    void invoke(Method m, Object obj, Object... args) throws Exception {
        if(matches(m)) System.out.println("[before]");
        m.invoke(obj, args);
        if(matches(m)) System.out.println("[after]");
    }
}


class MyClass {
    void aaa() {
        System.out.println("aaa() is called.");
    }
    void aaa2() {
        System.out.println("aaa2() is called.");
    }
    void bbb() {
        System.out.println("bbb() is called.");
    }
}
*/



/*
[before]
aaa() is called.
[after]

[before]
aaa2() is called.
[after]

bbb() is called.
*/


//////////////////////////////



public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for(Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }
    }
}

class MyAdvice { // Advice: 자동 추가할 부가적인 코드
    void invoke(Method m, Object obj, Object... args) throws Exception { // around Advice (before + after)
        if(m.getAnnotation(Transactional.class)!=null) System.out.println("[before]"); // before Advice
        m.invoke(obj, args); // 메서드 중간에는 Advice가 올 수 없음.
        if(m.getAnnotation(Transactional.class)!=null) System.out.println("[after]"); // after Advice
    }
}


class MyClass {
    @Transactional
    void aaa() {
        System.out.println("aaa() is called.");
    }
    void aaa2() {
        System.out.println("aaa2() is called.");
    }
    void bbb() {
        System.out.println("bbb() is called.");
    }
}

/*
[before]
aaa() is called.
[after]

aaa2() is called.

bbb() is called.
*/











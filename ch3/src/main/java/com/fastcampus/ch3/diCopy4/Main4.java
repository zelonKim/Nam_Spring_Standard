package com.fastcampus.ch3.diCopy4;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
class Car {
    // 자동 객체 연결
//    @Autowired
//    Engine engine;

//    @Autowired
//    Door door;


    @Resource
    Engine engine;

    @Resource
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}


@Component
class Engine {}

@Component
class Door {}


class AppContext {
    Map myMap;

    AppContext() {
        myMap = new HashMap();
        doComponentScan();
        doAutowired();
        doResource();
    }

    private void doResource() { // @Resource가 붙어있으면 Map에서 '이름'에 맞는 객체를 찾아 연결함.
        try {
            for(Object bean : myMap.values()) {
                for(Field fld : bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Resource.class) != null)
                        fld.set(bean, getBean(fld.getName()));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private void doAutowired() { // @Autowired가 붙어있으면 Map에서 '타입'에 맞는 객체를 찾아 연결함.
        try {
            for(Object bean : myMap.values()) {
                for(Field fld : bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Autowired.class) != null)
                        fld.set(bean, getBean(fld.getType()));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doComponentScan() {
        try {
            ClassLoader cl = AppContext.class.getClassLoader(); // 클래스 로더를 가져옴.
            ClassPath cp = ClassPath.from(cl); // 클래스 로더로부터 클래스 패스를 가져옴.

            Set<ClassPath.ClassInfo> set = cp.getTopLevelClasses("com.fastcampus.ch3.diCopy4"); // 패키지에서 클래스 목록들을 가져옴.

            for(ClassPath.ClassInfo ci : set) {
                Class clazz = ci.load(); // 클래스 정보를 로드하여 클래스를 가져옴.
                Component ct = (Component)clazz.getAnnotation(Component.class); // @Component 애너테이션이 붙은 클래스들을 가져옴.
                if(ct != null) {
                    String id = StringUtils.uncapitalize(ci.getSimpleName()); // 클래스 정보로부터 해당 클래스명을 가져옴.
                    myMap.put(id, clazz.newInstance()); // @Component 애너테이션이 붙은 클래스들을 맵에 저장함.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        Object getBean(String key) { // 이름으로 객체를 찾음. (by Name)
            return myMap.get(key);
        }

        Object getBean(Class clazz) {
            for(Object obj: myMap.values()) {
                if(clazz.isInstance(obj)) return obj;
            }
            return null;
        }
}

public class Main4 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();

        Car car = (Car)ac.getBean("car");
        Engine engine = (Engine)ac.getBean("engine");
        Door door = (Door)ac.getBean(Door.class);

        // 수동 객체 연결
//        car.engine = engine;
//        car.door = door;

        System.out.println(car); // Car{engine=com.fastcampus.ch3.diCopy4.Engine@1d371b2d, door=com.fastcampus.ch3.diCopy4.Door@543c6f6d}
        System.out.println(engine); // com.fastcampus.ch3.diCopy4.Engine@1d371b2d
        System.out.println(door);// com.fastcampus.ch3.diCopy4.Door@543c6f6d



    }
}

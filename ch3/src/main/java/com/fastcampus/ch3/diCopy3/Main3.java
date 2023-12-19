package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


@Component
class Color {}

class Design {}


class AppContext {
    Map myMap;

    AppContext() {
        myMap = new HashMap();
        doComponentScan();
    }

    private void doComponentScan() {
        try {
            ClassLoader cl = AppContext.class.getClassLoader(); // 클래스 로더를 가져옴.
            ClassPath cp = ClassPath.from(cl); // 클래스 로더로부터 클래스 패스를 가져옴.

            Set<ClassPath.ClassInfo> set = cp.getTopLevelClasses("com.fastcampus.ch3.diCopy3"); // 패키지에서 클래스 목록들을 가져옴.

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

        Object getBean(Class clazz) { // 타입으로 객체를 찾음. (by Type)
            for(Object obj: myMap.values()) {
                if(clazz.isInstance(obj)) return obj;
            }
            return null;
        }
}

public class Main3 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();

        Color color = (Color)ac.getBean("color");
        Design design = (Design)ac.getBean("design");

        System.out.println(color); // com.fastcampus.ch3.diCopy3.Color@491cc5c9
        System.out.println(design); // null


        Color color2 = (Color)ac.getBean(Color.class);
        Design design2 = (Design)ac.getBean(Design.class);

        System.out.println(color2); // com.fastcampus.ch3.diCopy3.Color@1b26f7b2
        System.out.println(design2); // null
    }
}

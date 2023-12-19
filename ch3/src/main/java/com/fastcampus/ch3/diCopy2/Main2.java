package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


class Color {}
class Red extends Color{}
class Design {}

class AppContext {
    Map myMap;

    AppContext() {
        myMap = new HashMap();

        try {
            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

            myMap = new HashMap(p); // 프로퍼티에 저장된 내용을 Map에 저장함.

            for(Object key : myMap.keySet()) {
                Class clazz = Class.forName((String)myMap.get(key));
                myMap.put(key, clazz.newInstance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        Object getBean(String key) {
            return myMap.get(key);
        }
    }


public class Main2 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();
        Color color = (Color)ac.getBean("color"); // com.fastcampus.ch3.diCopy2.Red@22f71333
        Design design = (Design)ac.getBean("design"); // com.fastcampus.ch3.diCopy2.Design@13969fbe

        System.out.println(color);
        System.out.println(design);
    }


}

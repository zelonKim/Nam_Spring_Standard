package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Color {}
class Red extends Color{}
class Green extends Color{}
class Blue extends Color{}


public class Main1 {
    public static void main(String[] args) throws Exception{
        Color red = getColor("red");
        Color green = getColor("green");
        Color blue = getColor("blue");
        Color color = getColor("color");

        System.out.println(red); // com.fastcampus.ch3.diCopy1.Red@22f71333
        System.out.println(green); // com.fastcampus.ch3.diCopy1.Green@13969fbe
        System.out.println(blue); // com.fastcampus.ch3.diCopy1.Blue@6aaa5eb0
        System.out.println(color); // com.fastcampus.ch3.diCopy1.Color@3498ed
    }

    static Color getColor(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));
        return (Color)clazz.newInstance();
    }
}

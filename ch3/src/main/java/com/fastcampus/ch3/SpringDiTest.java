//package com.fastcampus.ch3;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//
//@Component
//class Engine {} // <bean id="engine" class="com.fastcampus.ch3.Engine" />
//
//@Component
//class SuperEngine extends Engine{}
//
//@Component
//class TurboEngine extends Engine{}
//
//@Component
//class Door {}
//
//@Component
//class Car {
//    @Value("red") String color;
//    @Value("100") int oil;
//    @Autowired Engine engine;
//    @Autowired Door[] doors;
//
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
////    public void setColor(String color) {
////        this.color = color;
////    }
////
////    public void setOil(int oil) {
////        this.oil = oil;
////    }
////
////    public void setEngine(Engine engine) {
////        this.engine = engine;
////    }
////
////    public void setDoors(Door[] doors) {
////        this.doors = doors;
////    }
//}
//
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//        Car car = (Car) ac.getBean("car");
//        Engine engine = (Engine) ac.getBean("engine");
//        Door door = (Door) ac.getBean("door");
//
//
////        car.setColor("red");
////        car.setOil(100);
////        car.setEngine(engine);
////        car.setDoors(new Door[] {ac.getBean("door", Door.class), (Door)ac.getBean("door")});
//
//
//
//        System.out.println(car); // Car{color='red', oil=100, engine=com.fastcampus.ch3.Engine@f99f5e0, doors=[com.fastcampus.ch3.Door@26adfd2d]}
//        System.out.println(engine); // com.fastcampus.ch3.Engine@24fcf36f
//        System.out.println(door); // com.fastcampus.ch3.Door@10feca44
//    }
//}

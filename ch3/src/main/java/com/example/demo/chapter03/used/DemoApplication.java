package com.example.demo.chapter03.used;

import org.springframework.beans.factory.annotation.Autowired;


public class DemoApplication {

    @Autowired // MorningGreat 인스턴스가 greet필드에 주입됨.
    Greet greet;

    private void execute() {
        greet.greeting(); // 좋은 아침입니다.
    }

}

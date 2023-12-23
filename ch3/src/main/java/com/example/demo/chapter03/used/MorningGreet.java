package com.example.demo.chapter03.used;

import org.springframework.stereotype.Component;

@Component // MorningGreat 인스턴스가 생성됨.
public class MorningGreet implements Greet {
    @Override
    public void greeting() {
        System.out.println("좋은 아침입니다.");
    }
}

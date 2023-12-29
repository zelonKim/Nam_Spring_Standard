package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleRestController {
//    @GetMapping("/ajax")
//    public String ajax() {
//        return "ajax";
//    }

    @PostMapping("/send")
    @ResponseBody // 클래스에 @RestController 애너테이션을 붙일 경우, 메서드에서 @ResponseBody 애너테이션을 생략할 수 있음.
    public Person test(@RequestBody Person p) { // jackson-databind가 클라이언트로부터 요청받은 'JSON문자열'을 '자바객체'로 변환해줌.
        // @RequestBody는 요청 바디(본문)를 매개변수에 담아줌.
        System.out.println(p); // Person{name='abc', age=10}
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p; // jackson-databind가 '자바객체'를 'JSON문자열'로 변환하여 클라이언트로 응답함.
        // @ResponseBody는 응답 바디(본문)를 반환해줌.
    }
}








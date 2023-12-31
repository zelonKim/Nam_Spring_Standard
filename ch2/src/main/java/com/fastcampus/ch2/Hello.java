package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 1. 원격 프로그램 등록
public class Hello {
	int iv = 10;
	
	@RequestMapping("/hello") // 2. URL과 메서드 연결
	private void main() { 
		// 인스턴스 메서드일 경우, 자동으로 톰캣 내부에서 객체를 생성해줌.
		System.out.println("hello");
		System.out.println(iv);
	}
}

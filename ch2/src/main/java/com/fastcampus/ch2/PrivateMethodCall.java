package com.fastcampus.ch2;

import java.lang.reflect.Method; // Reflection API: 클래스 정보를 얻고, 이를 다룰 수 있도록 해줌.

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception{
//		Hello h = new Hello();
//		h.main(); // Hello클래스의 main메서드가 private이라 호출 불가
		
		
		Class hc = Class.forName("com.fastcampus.ch2.Hello"); // Hello클래스의 Class객체를 얻어옴.
		Hello h = (Hello)hc.newInstance(); // Class객체가 가진 정보로 객체를 생성함.
		Method m = hc.getDeclaredMethod("main"); //  Class객체가 가진 정보로 해당 메서드를 얻어옴.
		m.setAccessible(true); // main메서드를 접근가능하게 함.
		m.invoke(h); // 콘솔에 "hello 10" 이 출력됨.
		// Hello클래스의 main메서드가 private임에도 'Reflection API를 통해' 호출 가능
	}
}

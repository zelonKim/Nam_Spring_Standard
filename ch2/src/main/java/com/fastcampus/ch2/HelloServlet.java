package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// 서블릿이 초기화될때 자동 호출됨.
		System.out.println("init 메서드가 호출되었습니다.");
	}
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		System.out.println("service 메서드가 호출되었습니다.");
	}
	

	@Override
	public void destroy() {
		System.out.println("destroy 메서드가 호출되었습니다.");
	}

}


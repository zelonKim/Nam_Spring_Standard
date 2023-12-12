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
		// ������ �ʱ�ȭ�ɶ� �ڵ� ȣ���.
		System.out.println("init �޼��尡 ȣ��Ǿ����ϴ�.");
	}
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		System.out.println("service �޼��尡 ȣ��Ǿ����ϴ�.");
	}
	

	@Override
	public void destroy() {
		System.out.println("destroy �޼��尡 ȣ��Ǿ����ϴ�.");
	}

}


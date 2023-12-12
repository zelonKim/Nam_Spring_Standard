package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTeller {
	
	@RequestMapping("/getYoil")
	public void main(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 1. 입력
		String year = req.getParameter("year"); 
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		
		
		//////////////////////////////////
		
		
		// 2. 처리
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " 일월화수목금토".charAt(dayOfWeek);

		
		////////////////////////////////////
		
		
		// 3. 출력
		res.setContentType("text/html"); // 컨텐츠의 타입을 설정해줌.
		res.setCharacterEncoding("utf-8"); // 인코딩 방식을 설정해줌.
		
		PrintWriter result = res.getWriter();// 브라우저로의 출력 스트림을 얻음.
		
		result.println(year + "년 " + month + "월 " + day + "일은 ");
		result.println(yoil + "요일입니다.");

	}

}

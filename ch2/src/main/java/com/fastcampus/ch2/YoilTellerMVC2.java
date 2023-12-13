package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC2 { 
	@RequestMapping("/getYoilMVC2")
	public String main(MyDate date, Model mod) {
		
		// 유효성 검사
		if(!isValid(date))
			return "YoilError";  
		
		// 요일 계산
		char yoil = getYoil(date);

		// 계산한 결과를 모델객체에 저장
		mod.addAttribute("myDate", date);  
		mod.addAttribute("myYoil", yoil);
		
	return "Yoil"; 
	} 

	
	
	private boolean isValid(MyDate date) {
		return true;
	}


	private char getYoil(MyDate date) {
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char result = " 일월화수목금토".charAt(dayOfWeek);
		return result;
	}
	
}

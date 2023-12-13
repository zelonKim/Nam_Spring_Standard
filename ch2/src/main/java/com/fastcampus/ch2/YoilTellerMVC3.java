package com.fastcampus.ch2;

import java.util.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class YoilTellerMVC3 {
	@RequestMapping("/getYoilMVC3") 
	public String main(@ModelAttribute("myDate") MyDate date)  { // Model에 "myDate"라는 속성명으로 date 매개변수값(속성값)이 저장됨. 

		
		// 유효성 검사
		if(!isValid(date))
			return "YoilError"; 
			
		// 요일 계산
		getYoil(date);

	return "Yoil"; 
	} 

	
	private boolean isValid(MyDate date) {
		return true;
	}

	
    private  @ModelAttribute("myYoil") char getYoil(MyDate date) { // Model에 "myYoil"라는 속성명으로" result 반환값(속성값)이 저장됨. 
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char result = " 일월화수목금토".charAt(dayOfWeek);
		return result;
	}
}
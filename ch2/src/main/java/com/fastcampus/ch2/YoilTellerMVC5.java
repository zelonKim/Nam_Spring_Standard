package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class YoilTellerMVC5 {
	@RequestMapping("/getYoilMVC5") 
	public String main(MyDate date, BindingResult br)  { 
		
		
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

	
    private  @ModelAttribute("myYoil") char getYoil(MyDate date) {
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char result = " 일월화수목금토".charAt(dayOfWeek);
		return result;
	}
	
}

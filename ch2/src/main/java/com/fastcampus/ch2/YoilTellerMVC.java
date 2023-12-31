package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class YoilTellerMVC { // http://localhost:8080/ch2/getYoilMVC?year=2023&month=12&day=25
	@RequestMapping("/getYoilMVC") // URL의 쿼리스트링을 통해 전달해준 데이터를 연결된 메서드의 해당 매개변수로 받아줌. (단, 쿼리스트링의 파라미터명과 메서드의 매개변수명은 서로 같아야 함. / 쿼리스트링의 파라미터 타입과 메서드의 매개변수 타입은 서로 다르더라도 자동 타입변환됨.)
	public String main(int year, int month, int day,  Model mod) throws IOException {
		
		// 유효성 검사
		if(!isValid(year, month, day))
			return "YoilError";  // src/main/webapp/WEB-INF/views/yoilError.jsp 뷰를 사용하여 출력하도록 함.
			// 뷰 사용 기본경로는 servlet-context.xml파일에 지정되어 있음.
		
		// 요일 계산
		char yoil = getYoil(year, month, day);

		
		// 계산한 결과를 모델객체에 저장
		mod.addAttribute("Year", year);  
		mod.addAttribute("Month", month);
		mod.addAttribute("Day", day);
		mod.addAttribute("Yoil", yoil);
		
		
	return "Yoil"; // src/main/webapp/WEB-INF/views/Yoil.jsp 뷰를 사용하여 출력하도록 함.
	} 

	
	private boolean isValid(int yea, int month, int day) {
		return true;
	}


	private char getYoil(int yea, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(yea, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}
	
}

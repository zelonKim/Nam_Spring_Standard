package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class RequestParamTest {
	
	@RequestMapping("/test1") 
	public String main1(@RequestParam(value="year", required=false) String year) { // required=false일 경우, @RequestParam() 전체를 생략할 수 있음.
		
		System.out.println(year);
		return "Yoil";
	}
	//	http://localhost/ch2/test1       --->   콘솔: null  / 뷰: 올해는 null 입니다. 
	//	http://localhost/ch2/test1?year  --->   콘솔: ""  / 뷰: 올해는 입니다.  
	// 	http://localhost:8080/ch2/test1?year=2023   --->  콘솔: 2023  /  올해는 2023 입니다. 
	
	
	//////////////////////////////////

	
	@RequestMapping("/test2") 
	public String main2(String year) {
										
		System.out.println(year); 
		return "Yoil"; 
	}
	// http://localhost:8080/ch2/test1   --->   콘솔: null  /  뷰: 올해는 null 입니다.
	// http://localhost:8080/ch2/test1?year   --->  콘솔: ""  /  뷰: 올해는 입니다.
	// http://localhost:8080/ch2/test1?year=2023   --->  콘솔: 2023  /  뷰: 올해는 2023 입니다.
	
	
	
	
	////////////////////////////////////////////////////////
	
	
	

	@RequestMapping("/test3")
	public String main3(@RequestParam(value="year", required=true) String year) {  // required=true일 경우, @RequestParam() 괄호 부분을 생략할 수 있음.
		
		System.out.println(year);
		return "Yoil";	
	}
	
	//	http://localhost/ch2/test3         ---->  콘솔:   / 뷰: (에러 발생) 
	//	http://localhost/ch2/test3?year    ---->   콘솔: ""  /  뷰: 올해는 입니다.
	//	http://localhost/ch2/test3?year=2023   --->  콘솔: 2023  /  뷰: 올해는 2023 입니다. 

	
	///////////////////////////////
	
	
	
	@RequestMapping("/test4")
	public String main4(@RequestParam String year) {  
		
		System.out.println(year);
		return "Yoil";	
	}
	
	//	http://localhost/ch2/test4        ---->  콘솔:   / 뷰: (에러 발생)
	//	http://localhost/ch2/test4?year    ---->   콘솔: ""  /  뷰: 올해는 입니다.
	//	http://localhost/ch2/test4?year=2023   --->  콘솔: 2023  /  뷰: 올해는 2023 입니다. 
	
	
	
	/////////////////////////////////////
	
	
	
	@RequestMapping("/test5") 
	public String main5(@RequestParam(value="year", required=true, defaultValue="2000") String year) { // defaultValue="기본값"을 통해 해당 파라미터값이 주어지지 않았을 때의 기본값을 미리 설정해줄 수도 있음.
		
		System.out.println(year);
		return "Yoil";
	}
	//	http://localhost/ch2/test5       --->   콘솔: 2000  / 뷰: 올해는 null 입니다. (정상 실행)
	//	http://localhost/ch2/test5?year  --->   콘솔: ""  / 뷰: 올해는 입니다.  
	// 	http://localhost:8080/ch2/test5?year=2023   --->  콘솔: 2023  /  올해는 2023 입니다. 
	
	
} // class
package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class RequestParamTest {
	
	@RequestMapping("/test1") 
	public String main1(@RequestParam(value="year", required=false) String year) { // required=false�� ���, @RequestParam() ��ü�� ������ �� ����.
		
		System.out.println(year);
		return "Yoil";
	}
	//	http://localhost/ch2/test1       --->   �ܼ�: null  / ��: ���ش� null �Դϴ�. 
	//	http://localhost/ch2/test1?year  --->   �ܼ�: ""  / ��: ���ش� �Դϴ�.  
	// 	http://localhost:8080/ch2/test1?year=2023   --->  �ܼ�: 2023  /  ���ش� 2023 �Դϴ�. 
	
	
	//////////////////////////////////

	
	@RequestMapping("/test2") 
	public String main2(String year) {
										
		System.out.println(year); 
		return "Yoil"; 
	}
	// http://localhost:8080/ch2/test1   --->   �ܼ�: null  /  ��: ���ش� null �Դϴ�.
	// http://localhost:8080/ch2/test1?year   --->  �ܼ�: ""  /  ��: ���ش� �Դϴ�.
	// http://localhost:8080/ch2/test1?year=2023   --->  �ܼ�: 2023  /  ��: ���ش� 2023 �Դϴ�.
	
	
	
	
	////////////////////////////////////////////////////////
	
	
	

	@RequestMapping("/test3")
	public String main3(@RequestParam(value="year", required=true) String year) {  // required=true�� ���, @RequestParam() ��ȣ �κ��� ������ �� ����.
		
		System.out.println(year);
		return "Yoil";	
	}
	
	//	http://localhost/ch2/test3         ---->  �ܼ�:   / ��: (���� �߻�) 
	//	http://localhost/ch2/test3?year    ---->   �ܼ�: ""  /  ��: ���ش� �Դϴ�.
	//	http://localhost/ch2/test3?year=2023   --->  �ܼ�: 2023  /  ��: ���ش� 2023 �Դϴ�. 

	
	///////////////////////////////
	
	
	
	@RequestMapping("/test4")
	public String main4(@RequestParam String year) {  
		
		System.out.println(year);
		return "Yoil";	
	}
	
	//	http://localhost/ch2/test4        ---->  �ܼ�:   / ��: (���� �߻�)
	//	http://localhost/ch2/test4?year    ---->   �ܼ�: ""  /  ��: ���ش� �Դϴ�.
	//	http://localhost/ch2/test4?year=2023   --->  �ܼ�: 2023  /  ��: ���ش� 2023 �Դϴ�. 
	
	
	
	/////////////////////////////////////
	
	
	
	@RequestMapping("/test5") 
	public String main5(@RequestParam(value="year", required=true, defaultValue="2000") String year) { // defaultValue="�⺻��"�� ���� �ش� �Ķ���Ͱ��� �־����� �ʾ��� ���� �⺻���� �̸� �������� ���� ����.
		
		System.out.println(year);
		return "Yoil";
	}
	//	http://localhost/ch2/test5       --->   �ܼ�: 2000  / ��: ���ش� null �Դϴ�. (���� ����)
	//	http://localhost/ch2/test5?year  --->   �ܼ�: ""  / ��: ���ش� �Դϴ�.  
	// 	http://localhost:8080/ch2/test5?year=2023   --->  �ܼ�: 2023  /  ���ش� 2023 �Դϴ�. 
	
	
} // class
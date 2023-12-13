package com.fastcampus.ch2;

import java.util.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class YoilTellerMVC3 {
	@RequestMapping("/getYoilMVC3") 
	public String main(@ModelAttribute("myDate") MyDate date)  { // Model�� "myDate"��� �Ӽ������� date �Ű�������(�Ӽ���)�� �����. 

		
		// ��ȿ�� �˻�
		if(!isValid(date))
			return "YoilError"; 
			
		// ���� ���
		getYoil(date);

	return "Yoil"; 
	} 

	
	private boolean isValid(MyDate date) {
		return true;
	}

	
    private  @ModelAttribute("myYoil") char getYoil(MyDate date) { // Model�� "myYoil"��� �Ӽ�������" result ��ȯ��(�Ӽ���)�� �����. 
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char result = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		return result;
	}
}